package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unisinos.tripverse.model.shared.DtoResponse;
import unisinos.tripverse.service.UploadService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/v1/uploads")
@Slf4j
@Tag(name = "Uploads", description = "Endpoints que administra os uploads dos usuários")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @GetMapping("{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        try {
            var file = uploadService.read(filename);

            if(file == null){
                return ResponseEntity.notFound().build();
            }

            var contentType = Files.probeContentType(Paths.get(file.getURI()));

            String contentDisposition = "inline; filename=\"" + file.getFilename() + "\"";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(file);

        } catch (IOException e) {

            //var message = e.getMessage();

            return ResponseEntity
            		.status(HttpStatus.INTERNAL_SERVER_ERROR)
            		.build();
        }
    }

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Upar um arquivo e receber a url estática.")
    public ResponseEntity<DtoResponse<String>> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(DtoResponse.error("Please select a file to upload."));
            }

            var url = uploadService.upload(file);

            return ResponseEntity.ok(DtoResponse.success(url));

        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(DtoResponse.error("Failed to upload file: " + e.getMessage()));
        }
    }
}

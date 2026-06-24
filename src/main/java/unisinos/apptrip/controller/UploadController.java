package unisinos.apptrip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unisinos.apptrip.dto.UploadDto;
import unisinos.apptrip.model.shared.DtoResponse;
import unisinos.apptrip.service.UploadService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/v1/uploads")
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;

    @GetMapping("{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {
        var file = uploadService.read(filename);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        String detected = Files.probeContentType(Paths.get(file.getURI()));
        MediaType contentType = detected == null ?MediaType.APPLICATION_OCTET_STREAM : MediaType.parseMediaType(detected);
        return ResponseEntity.ok()
                .contentType(contentType)
                .header(HttpHeaders.ACCEPT_RANGES, "bytes")
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DtoResponse<UploadDto>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponse.success(uploadService.upload(file)));
    }
}

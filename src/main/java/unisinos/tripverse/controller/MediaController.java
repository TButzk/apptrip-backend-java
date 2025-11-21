package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.map.MediaMapper;
import unisinos.tripverse.dto.MediaDto;
import unisinos.tripverse.dto.CreateMediaDto;
import unisinos.tripverse.model.shared.DtoResponse;
import unisinos.tripverse.model.shared.PageInfo;
import unisinos.tripverse.model.shared.PageResponse;
import unisinos.tripverse.service.MediaService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/posts/{postId}/media")
@Tag(name = "Media", description = "Endpoints responsáveis por administrar mídias (fotos/vídeos) de um Post")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private MediaMapper mediaMapper;

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria uma mídia dentro de um post")
    public ResponseEntity<DtoResponse<MediaDto>> create(@PathVariable String postId, @RequestBody CreateMediaDto create){
        var media = mediaService.create(UUID.fromString(postId), create);
        return ResponseEntity.ok(DtoResponse.success(mediaMapper.toDto(media)));
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma mídia de um post pelos ids")
    public ResponseEntity<DtoResponse<MediaDto>> get(@PathVariable String postId, @PathVariable String id){
        var media = mediaService.get(UUID.fromString(id));
        return ResponseEntity.ok(DtoResponse.success(mediaMapper.toDto(media)));
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de mídias em um post")
    public ResponseEntity<PageResponse<MediaDto>> getList(@PathVariable String postId, @RequestParam int skip, @RequestParam int take){
        var mediaPage = mediaService.get(UUID.fromString(postId), take, skip);
        var response = PageResponse.success(mediaPage.map(mediaMapper::toDto).toList(), PageInfo.fromPage(mediaPage));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Remove uma mídia de um post")
    public ResponseEntity<DtoResponse<MediaDto>> delete(@PathVariable String postId, @PathVariable String id){
        var media = mediaService.delete(UUID.fromString(id));
        return ResponseEntity.ok(DtoResponse.success(mediaMapper.toDto(media)));
    }
}
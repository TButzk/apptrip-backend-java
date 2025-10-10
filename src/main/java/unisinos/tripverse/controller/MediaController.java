package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.media.CreateMediaDTO;
import unisinos.tripverse.model.media.MediaDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts/{postId}/medias")
@Tag(name = "Medias", description = "Endpoints relacionados a administração das medias de um Post")
public class MediaController {

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria uma media em um post")
    public MediaDTO create(@PathVariable String postId, @RequestBody CreateMediaDTO create){
        return new MediaDTO();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna todas as medias de um post")
    public List<MediaDTO> get(@PathVariable String postId){
        return List.of(new MediaDTO());
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma media de um post pelos ids")
    public MediaDTO getById(@PathVariable String postId, @PathVariable String id){
        return new MediaDTO();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Delete uma media de um post")
    public MediaDTO delete(@PathVariable String postId, @PathVariable String id){
        return new MediaDTO();
    }
}

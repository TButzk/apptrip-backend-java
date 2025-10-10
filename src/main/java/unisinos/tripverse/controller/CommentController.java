package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.comment.CommentDTO;
import unisinos.tripverse.model.comment.SetCommentDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts/{postId}/comments")
@Tag(name = "Comments", description = "Endpoints responsáveis por administrar os comentários de um Post")
public class CommentController {

    @PutMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza ou cria um comment dentro de um post")
    public CommentDTO create(@PathVariable String postId, @RequestBody SetCommentDTO post){
        return CommentDTO.builder().build();
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna um comment de um post pelos ids")
    public CommentDTO get(@PathVariable String postId, @PathVariable String id){
        return CommentDTO.builder().build();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de comments em um post")
    public List<CommentDTO> getList(@PathVariable String postId, @RequestParam int skip, @RequestParam int limit){
        return List.of(CommentDTO.builder().build());
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Remove um comment de um Post")
    public CommentDTO delete(@PathVariable String postId, @PathVariable String id){
        return CommentDTO.builder().build();
    }
}

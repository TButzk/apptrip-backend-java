package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.comment.CommentDto;
import unisinos.tripverse.model.comment.SetCommentDto;

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
    public CommentDto create(@PathVariable String postId, @RequestBody SetCommentDto post){
        return CommentDto.builder().build();
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna um comment de um post pelos ids")
    public CommentDto get(@PathVariable String postId, @PathVariable String id){
        return CommentDto.builder().build();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de comments em um post")
    public List<CommentDto> getList(@PathVariable String postId, @RequestParam int skip, @RequestParam int limit){
        return List.of(CommentDto.builder().build());
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Remove um comment de um Post")
    public CommentDto delete(@PathVariable String postId, @PathVariable String id){
        return CommentDto.builder().build();
    }
}

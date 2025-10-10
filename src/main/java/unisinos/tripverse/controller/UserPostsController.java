package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.post.PostDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/{id}/posts")
@Tag(name = "Users", description = "Endpoints dos Posts de cada usuário")
public class UserPostsController {

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de posts de um usuário")
    public List<PostDTO> get(@PathVariable String id){
        return List.of(PostDTO.builder().build());
    }
}

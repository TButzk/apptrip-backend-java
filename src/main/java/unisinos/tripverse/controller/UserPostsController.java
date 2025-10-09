package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.post.PostDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/{id}/posts")
@Tag(name = "Posts", description = "Controller responsável pela administração dos posts de cada usuário")
public class UserPostsController {

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public List<PostDTO> get(@PathVariable String id){
        return List.of(new PostDTO());
    }
}

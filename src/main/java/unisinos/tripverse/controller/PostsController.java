package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.post.CreatePostDTO;
import unisinos.tripverse.model.post.PostDTO;
import unisinos.tripverse.model.post.UpdatePostDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@Tag(name = "Posts", description = "Controller responsável pela administração dos posts de cada usuário")
public class PostsController {

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public PostDTO create(@RequestBody CreatePostDTO post){
        return new PostDTO();
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public PostDTO get(@PathVariable String id){
        return new PostDTO();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public List<PostDTO> getList(@RequestParam int skip, @RequestParam int limit){
        return List.of(new PostDTO());
    }

    @PatchMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public PostDTO update(@RequestBody UpdatePostDTO udpate){
        return new PostDTO();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public PostDTO delete(@PathVariable String id){
        return new PostDTO();
    }
}

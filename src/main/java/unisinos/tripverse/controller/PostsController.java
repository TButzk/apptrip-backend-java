package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Posts", description = "Endpoint do CRUD dos Posts")
public class PostsController {

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria um post")
    public PostDTO create(@RequestBody CreatePostDTO post){
        return PostDTO.builder().build();
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna um post pelo id")
    public PostDTO get(@PathVariable String id){
        return PostDTO.builder().build();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de posts")
    public List<PostDTO> getList(@RequestParam int skip, @RequestParam int limit){
        return List.of(PostDTO.builder().build());
    }

    @PatchMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza um post pelo id")
    public PostDTO update(@RequestBody UpdatePostDTO udpate){
        return PostDTO.builder().build();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Deleta um post pelo id")
    public PostDTO delete(@PathVariable String id){
        return PostDTO.builder().build();
    }
}

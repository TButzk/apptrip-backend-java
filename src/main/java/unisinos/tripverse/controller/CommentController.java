package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.comment.CommentDTO;
import unisinos.tripverse.model.comment.CreateCommentDTO;
import unisinos.tripverse.model.comment.UpdateCommentDTO;
import unisinos.tripverse.model.post.CreatePostDTO;
import unisinos.tripverse.model.post.PostDTO;
import unisinos.tripverse.model.post.UpdatePostDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts/{id}/comments")
@Tag(name = "Posts", description = "Controller responsável pela administração dos posts de cada usuário")
public class CommentController {
    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public CommentDTO create(@RequestBody CreateCommentDTO post){
        return new CommentDTO();
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public CommentDTO get(@PathVariable String id){
        return new CommentDTO();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public List<CommentDTO> getList(@RequestParam int skip, @RequestParam int limit){
        return List.of(new CommentDTO());
    }

    @PatchMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public CommentDTO update(@RequestBody UpdateCommentDTO udpate){
        return new CommentDTO();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public CommentDTO delete(@PathVariable String id){
        return new CommentDTO();
    }
}

package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.map.PostMapper;
import unisinos.tripverse.model.post.CreatePostDto;
import unisinos.tripverse.model.post.PostDto;
import unisinos.tripverse.model.post.UpdatePostDto;
import unisinos.tripverse.model.shared.DtoResponse;
import unisinos.tripverse.model.shared.PageInfo;
import unisinos.tripverse.model.shared.PageResponse;
import unisinos.tripverse.service.PostService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/posts")
@Tag(name = "Posts", description = "Endpoint do CRUD dos Posts")
public class PostsController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria um post")
    public ResponseEntity<DtoResponse<PostDto>> create(@RequestBody CreatePostDto create){

        var post = postService.create(create);
        return ResponseEntity.ok(DtoResponse.success(postMapper.toDto(post)));
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna um post pelo id")
    public ResponseEntity<DtoResponse<PostDto>> get(@PathVariable String id){
        var post = postService.get(UUID.fromString(id));
        return ResponseEntity.ok(DtoResponse.success(postMapper.toDto(post)));
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de posts")
    public ResponseEntity<PageResponse<PostDto>> getList(@RequestParam int skip, @RequestParam int take){
        var posts = postService.get(take, skip);
        var response = PageResponse.success(posts.map(postMapper::toDto).toList(), PageInfo.fromPage(posts));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza um post pelo id")
    public ResponseEntity<DtoResponse<PostDto>> update(@PathVariable String id, @RequestBody UpdatePostDto update){
        var post = postService.update(UUID.fromString(id), update);
        return ResponseEntity.ok(DtoResponse.success(postMapper.toDto(post)));
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Deleta um post pelo id")
    public ResponseEntity<DtoResponse<PostDto>> delete(@PathVariable String id){
        var post = postService.delete(UUID.fromString(id));
        return ResponseEntity.ok(DtoResponse.success(postMapper.toDto(post)));
    }
}

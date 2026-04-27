package unisinos.apptrip.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisinos.apptrip.map.PostMapper;
import unisinos.apptrip.dto.PostDto;
import unisinos.apptrip.model.shared.PageInfo;
import unisinos.apptrip.model.shared.PageResponse;
import unisinos.apptrip.service.PostService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/users/{id}/posts")
@Tag(name = "Users", description = "Endpoints dos Posts de cada usuário")
public class UserPostsController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de posts de um usuário")
    public ResponseEntity<PageResponse<PostDto>> get(@PathVariable String id, @RequestParam int skip, @RequestParam int take){
        var posts = postService.get(UUID.fromString(id), take, skip);
        var response = PageResponse.success(posts.map(postMapper::toDto).toList(), PageInfo.fromPage(posts));
        return ResponseEntity.ok(response);
    }
}


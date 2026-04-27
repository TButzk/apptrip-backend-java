package unisinos.apptrip.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisinos.apptrip.map.CommentMapper;
import unisinos.apptrip.dto.CommentDto;
import unisinos.apptrip.dto.CreateCommentDto;
import unisinos.apptrip.dto.UpdateCommentDto;
import unisinos.apptrip.model.shared.DtoResponse;
import unisinos.apptrip.model.shared.PageInfo;
import unisinos.apptrip.model.shared.PageResponse;
import unisinos.apptrip.service.CommentService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/posts/{postId}/comments")
@Tag(name = "Comments", description = "Endpoints responsáveis por administrar os comentários de um Post")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria um comment dentro de um post")
    public ResponseEntity<DtoResponse<CommentDto>> create(@PathVariable String postId, @RequestBody CreateCommentDto create){
        var comment = commentService.create(UUID.fromString(postId), create);
        return ResponseEntity.ok(DtoResponse.success(commentMapper.toDto(comment)));
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna um comment de um post pelos ids")
    public ResponseEntity<DtoResponse<CommentDto>> get(@PathVariable String postId, @PathVariable String id){
        var comment = commentService.get(UUID.fromString(id));
        return ResponseEntity.ok(DtoResponse.success(commentMapper.toDto(comment)));
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de comments em um post")

    public ResponseEntity<PageResponse<CommentDto>> getList(@PathVariable String postId, @RequestParam int skip, @RequestParam int take){
        var comments = commentService.get(UUID.fromString(postId), take, skip);
        var response = PageResponse.success(comments.map(commentMapper::toDto).toList(), PageInfo.fromPage(comments));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza um comment de um post")
    public ResponseEntity<DtoResponse<CommentDto>> update(@PathVariable String postId, @PathVariable String id, @RequestBody UpdateCommentDto update){
        var comment = commentService.update(UUID.fromString(id), update);
        return ResponseEntity.ok(DtoResponse.success(commentMapper.toDto(comment)));
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Remove um comment de um post")
    public ResponseEntity<DtoResponse<CommentDto>> delete(@PathVariable String postId, @PathVariable String id){
        var comment = commentService.delete(UUID.fromString(id));
        return ResponseEntity.ok(DtoResponse.success(commentMapper.toDto(comment)));
    }
}


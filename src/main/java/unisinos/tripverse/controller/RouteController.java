package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unisinos.tripverse.model.post.PostDto;

import java.util.List;

@RestController
@RequestMapping("api/v1/routes/{id}")
@Tag(name = "Routes", description = "Endpoints que administra as rotas")
public class RouteController {

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna os posts relacionados a uma rota")
    public List<PostDto> get(@PathVariable String id){
        return List.of(PostDto.builder().build());
    }
}

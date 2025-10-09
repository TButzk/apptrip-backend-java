package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.media.CreateMediaDTO;
import unisinos.tripverse.model.media.MediaDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts/{id}/medias")
@Tag(name = "Posts", description = "Controller responsável pela administração dos posts de cada usuário")
public class MediaController {

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public MediaDTO create(@RequestBody CreateMediaDTO create){
        return new MediaDTO();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public List<MediaDTO> get(){
        return List.of(new MediaDTO());
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public MediaDTO getById(@PathVariable String id){
        return new MediaDTO();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public MediaDTO delete(@PathVariable String id){
        return new MediaDTO();
    }
}

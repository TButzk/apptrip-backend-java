package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.place.CreatePlaceDTO;
import unisinos.tripverse.model.place.PlaceDTO;
import unisinos.tripverse.model.post.PostDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/places")
@Tag(name = "Posts", description = "Controller responsável pela administração dos posts de cada usuário")
public class PlaceController {

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public PlaceDTO get(@PathVariable String id){
        return new PlaceDTO();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public List<PlaceDTO> get(@RequestParam int limit, @RequestParam int skip){
        return List.of(new PlaceDTO());
    }

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public PlaceDTO create(CreatePlaceDTO create){
        return new PlaceDTO();
    }
}

package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.place.CreatePlaceDTO;
import unisinos.tripverse.model.place.PlaceDTO;
import unisinos.tripverse.model.post.PostDTO;
import unisinos.tripverse.model.post.UpdatePlaceDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/places")
@Tag(name = "Places", description = "Endpoints relacionados ao CRUD de um Place")
public class PlaceController {

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna um place pelo id")
    public PlaceDTO get(@PathVariable String id){
        return new PlaceDTO();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de places")
    public List<PlaceDTO> get(@RequestParam int limit, @RequestParam int skip){
        return List.of(new PlaceDTO());
    }

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria um place")
    public PlaceDTO create(CreatePlaceDTO create){
        return new PlaceDTO();
    }

    @PatchMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza um place")
    public PlaceDTO update(@PathVariable String id, UpdatePlaceDTO update){
        return new PlaceDTO();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Deleta um place pelo id")
    public PlaceDTO delete(@PathVariable String id){
        return new PlaceDTO();
    }
}

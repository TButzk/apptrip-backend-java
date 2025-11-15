package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.place.CreatePlaceDto;
import unisinos.tripverse.model.place.PlaceDto;
import unisinos.tripverse.model.place.UpdatePlaceDto;

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
    public PlaceDto get(@PathVariable String id){
        return PlaceDto.builder().build();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de places")
    public List<PlaceDto> get(@RequestParam int limit, @RequestParam int skip){
        return List.of(PlaceDto.builder().build());
    }

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria um place")
    public PlaceDto create(CreatePlaceDto create){
        return PlaceDto.builder().build();
    }

    @PatchMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza um place")
    public PlaceDto update(@PathVariable String id, UpdatePlaceDto update){
        return PlaceDto.builder().build();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Deleta um place pelo id")
    public PlaceDto delete(@PathVariable String id){
        return PlaceDto.builder().build();
    }
}

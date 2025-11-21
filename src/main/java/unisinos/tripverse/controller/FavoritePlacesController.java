package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.dto.FavoritePlaceDto;
import unisinos.tripverse.dto.SetFavoritePlaceDto;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/{userId}/places")
@Tag(name = "FavoritePlaces", description = "Endpoints responsáveis por administrar os Favorite Places de um usuário")
public class FavoritePlacesController {

    @PutMapping("{placesId}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Adiciona ou atualiza um  favorite place de um usuário")
    public FavoritePlaceDto add(@PathVariable String userId, @PathVariable String placesId, @RequestBody SetFavoritePlaceDto set){
        return FavoritePlaceDto.builder().build();
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna os favorite places de um usuário")
    public List<FavoritePlaceDto> get(@PathVariable String userId){
        return List.of(FavoritePlaceDto.builder().build());
    }

    @DeleteMapping("{placesId}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Remove um favorite place de um usuário")
    public FavoritePlaceDto delete(@PathVariable String userId, @PathVariable String placesId){
        return FavoritePlaceDto.builder().build();
    }
}

package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import unisinos.tripverse.map.PlaceMapper;
import unisinos.tripverse.model.place.CreatePlaceDto;
import unisinos.tripverse.model.place.PlaceDto;
import unisinos.tripverse.model.place.UpdatePlaceDto;
import unisinos.tripverse.model.shared.DtoResponse;
import unisinos.tripverse.model.shared.PageInfo;
import unisinos.tripverse.model.shared.PageResponse;
import unisinos.tripverse.service.PlaceService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/places")
@Tag(name = "Places", description = "Endpoints relacionados ao CRUD de um Place")
public class PlaceController {
	
	@Autowired
	PlaceService placeService;
	
    @Autowired
    private PlaceMapper placeMapper;	
    
    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna um place pelo id")
    public ResponseEntity<DtoResponse<PlaceDto>> get(@PathVariable String id){
        var place = placeService.get(UUID.fromString(id));
        var response = DtoResponse.success(placeMapper.toDto(place)); 
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de places")
    public ResponseEntity<PageResponse<PlaceDto>> getList(@RequestParam int skip, @RequestParam int take){
        var places = placeService.get(take, skip);
        var response = PageResponse.success(places.map(placeMapper::toDto).toList(), PageInfo.fromPage(places));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria um place")
    public ResponseEntity<DtoResponse<PlaceDto>> create(@RequestBody CreatePlaceDto create){
        var place = placeService.create(create);
        var response = DtoResponse.success(placeMapper.toDto(place)); 
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza um place")
    public ResponseEntity<DtoResponse<PlaceDto>> update(@PathVariable String id, @RequestBody UpdatePlaceDto update){
        var place = placeService.update(UUID.fromString(id), update);
        var response = DtoResponse.success(placeMapper.toDto(place)); 
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Deleta um place pelo id")
    public ResponseEntity<DtoResponse<PlaceDto>> delete(@PathVariable String id){
        var place = placeService.delete(UUID.fromString(id));
        return ResponseEntity.ok(DtoResponse.success(placeMapper.toDto(place)));
    }
}

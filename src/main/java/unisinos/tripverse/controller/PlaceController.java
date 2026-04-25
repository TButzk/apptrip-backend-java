package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import unisinos.tripverse.exception.NotFoundException;
import unisinos.tripverse.map.PlaceMapper;
import unisinos.tripverse.model.Location;
import unisinos.tripverse.dto.CreatePlaceDto;
import unisinos.tripverse.dto.PlaceDto;
import unisinos.tripverse.dto.UpdatePlaceDto;
import unisinos.tripverse.model.shared.DtoResponse;
import unisinos.tripverse.model.shared.PageInfo;
import unisinos.tripverse.model.shared.PageResponse;
import unisinos.tripverse.service.LocationService;
import unisinos.tripverse.service.PlaceService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/places")
@Tag(name = "Places", description = "Endpoints relacionados ao CRUD de um Place")
public class PlaceController {
	
	@Autowired
	private PlaceService placeService;
	
    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    private LocationService locationService;
    
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

    @GetMapping("by-route/{routeId}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de places por rota")
    public ResponseEntity<PageResponse<PlaceDto>> getByRoute(@PathVariable String routeId, @RequestParam int skip, @RequestParam int take){
        var places = placeService.getByRoute(UUID.fromString(routeId), take, skip);
        var response = PageResponse.success(places.map(placeMapper::toDto).toList(), PageInfo.fromPage(places));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria um place")
    public ResponseEntity<DtoResponse<PlaceDto>> create(@RequestBody CreatePlaceDto create){

        if(create.getLatitude() != null && create.getLongitude() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DtoResponse.error("longitude is required when latitude is provided"));
        }
        if(create.getLongitude() != null && create.getLatitude() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DtoResponse.error("latitude is required when longitude is provided"));
        }

        Location location = null;

        if (!create.hasCoordinates()) {
            try{
                location = locationService.get(create.getFullAddress());
            }
            catch (NotFoundException exception){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DtoResponse.error(exception.getMessage()));
            }
        }

        var place = placeService.create(create, location);

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

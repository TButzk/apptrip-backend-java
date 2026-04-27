package unisinos.apptrip.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unisinos.apptrip.dto.PlaceDto;
import unisinos.apptrip.map.PlaceMapper;
import unisinos.apptrip.model.shared.PageInfo;
import unisinos.apptrip.model.shared.PageResponse;
import unisinos.apptrip.service.PlaceService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/routes/{routeId}/places")
@Tag(name = "Route Places", description = "Endpoints para listar os pontos/lugares de uma rota")
public class RoutePlacesController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceMapper placeMapper;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Nao encontrado.")
    @ApiResponse(responseCode = "400", description = "Erro na validacao dos dados enviados.")
    @Operation(summary = "Lista os pontos/lugares de uma rota com ordenacao por sequence e id")
    public ResponseEntity<PageResponse<PlaceDto>> getByRoute(
            @PathVariable String routeId,
            @RequestParam int skip,
            @RequestParam int take
    ) {
        var places = placeService.getByRoute(UUID.fromString(routeId), take, skip);
        var response = PageResponse.success(places.map(placeMapper::toDto).toList(), PageInfo.fromPage(places));
        return ResponseEntity.ok(response);
    }
}


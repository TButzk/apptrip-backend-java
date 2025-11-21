package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.map.RouteMapper;
import unisinos.tripverse.dto.CreateRouteDto;
import unisinos.tripverse.dto.RouteDto;
import unisinos.tripverse.model.shared.DtoResponse;
import unisinos.tripverse.model.shared.PageInfo;
import unisinos.tripverse.model.shared.PageResponse;
import unisinos.tripverse.service.RouteService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/routes")
@Tag(name = "Routes", description = "Endpoints que administra as rotas")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteMapper routeMapper;

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna um route pelo id")
    public ResponseEntity<DtoResponse<RouteDto>> get(@PathVariable String id){
        var route = routeService.get(UUID.fromString(id));
        return ResponseEntity.ok(DtoResponse.success(routeMapper.toDto(route)));
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna uma lista de routes")
    public ResponseEntity<PageResponse<RouteDto>> getList(@RequestParam int skip, @RequestParam int take){
        var routes = routeService.get(take, skip);
        var response = PageResponse.success(routes.map(routeMapper::toDto).toList(), PageInfo.fromPage(routes));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria um route")
    public ResponseEntity<DtoResponse<RouteDto>> create(@RequestBody CreateRouteDto create){
        var route = routeService.create(create);
        return ResponseEntity.ok(DtoResponse.success(routeMapper.toDto(route)));
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Deleta um route pelo id")
    public ResponseEntity<DtoResponse<RouteDto>> delete(@PathVariable String id){
        var route = routeService.delete(UUID.fromString(id));
        return ResponseEntity.ok(DtoResponse.success(routeMapper.toDto(route)));
    }
}

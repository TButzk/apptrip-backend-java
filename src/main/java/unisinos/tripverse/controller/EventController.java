package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.event.CreateEventDTO;
import unisinos.tripverse.model.event.EventDTO;
import unisinos.tripverse.model.event.UpdateEventDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/places/{placeId}/events")
@Tag(name = "Events", description = "Endpoints responsáveis pela administração de eventos em places")
public class EventController {

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Cria o evento em um place")
    public EventDTO create(@PathVariable String placeId, @RequestBody CreateEventDTO create){
        return new EventDTO();
    }

    @GetMapping    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna todos os eventos de um place")
    public List<EventDTO> get(@PathVariable String placeId){
        return List.of(new EventDTO());
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Retorna um evento de um place pelo id")
    public EventDTO getById(@PathVariable String placeId, @PathVariable String id){
        return new EventDTO();
    }

    @PatchMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Atualiza um evento de um place pelo id")
    public EventDTO update(@PathVariable String placeId, @PathVariable String id, @RequestBody UpdateEventDTO update){
        return new EventDTO();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    @Operation(summary = "Remove um evento de um place")
    public EventDTO delete(@PathVariable String placeId, @PathVariable String id){
        return new EventDTO();
    }
}

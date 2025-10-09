package unisinos.tripverse.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unisinos.tripverse.model.event.CreateEventDTO;
import unisinos.tripverse.model.event.EventDTO;
import unisinos.tripverse.model.event.UpdateEventDTO;
import unisinos.tripverse.model.place.CreatePlaceDTO;
import unisinos.tripverse.model.place.PlaceDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/places/{placeId}/events")
@Tag(name = "Posts", description = "Controller responsável pela administração dos posts de cada usuário")
public class EventController {

    @PostMapping("{placeId}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public EventDTO create(@PathVariable String placeId, @RequestBody CreateEventDTO create){
        return new EventDTO();
    }

    @GetMapping    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public List<EventDTO> get(@PathVariable String placeId, @RequestParam int limit, @RequestParam int skip){
        return List.of(new EventDTO());
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public EventDTO getById(@PathVariable String placeId, @PathVariable String id){
        return new EventDTO();
    }

    @PatchMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode =  "404", description = "Não encontrado.")
    @ApiResponse(responseCode =  "400", description = "Erro na validação dos dados enviados.")
    public EventDTO update(@PathVariable String placeId, @PathVariable String id, @RequestBody UpdateEventDTO update){
        return new EventDTO();
    }
}

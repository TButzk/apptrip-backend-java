package unisinos.tripverse.map;

import org.springframework.stereotype.Component;

import unisinos.tripverse.model.Route;
import unisinos.tripverse.dto.RouteDto;

@Component
public class RouteMapper {
	
    public RouteDto toDto(Route route){
        return RouteDto.builder()
                .id(route.getId())
                .name(route.getName())
                .userId(route.getUser().getId())
                .placeIds(route.getPlaceIds())
                .build();
    }
}

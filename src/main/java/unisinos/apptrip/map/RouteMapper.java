package unisinos.apptrip.map;

import org.springframework.stereotype.Component;

import unisinos.apptrip.model.Route;
import unisinos.apptrip.dto.RouteDto;

@Component
public class RouteMapper {
	
    public RouteDto toDto(Route route){
        return RouteDto.builder()
                .id(route.getId())
                .name(route.getName())
                .userId(route.getUser().getId())
                .placeIds(route.getPlaceIds())
            .status(route.getStatus())
            .publishedAt(route.getPublishedAt())
            .finalizedAt(route.getFinalizedAt())
                .build();
    }
}


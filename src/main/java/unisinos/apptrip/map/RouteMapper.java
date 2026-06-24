package unisinos.apptrip.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import unisinos.apptrip.model.Route;
import unisinos.apptrip.dto.RouteDto;
import unisinos.apptrip.repository.PlaceRepository;

@Component
@RequiredArgsConstructor
public class RouteMapper {
    private final PlaceRepository placeRepository;
	
    public RouteDto toDto(Route route){
        return RouteDto.builder()
                .id(route.getId())
                .name(route.getName())
                .userId(route.getUser().getId())
                .placeIds(route.getId() == null ?java.util.List.of() : placeRepository.findIdsByRouteId(route.getId()))
            .status(route.getStatus())
            .publishedAt(route.getPublishedAt())
            .finalizedAt(route.getFinalizedAt())
            .minimumDistanceMeters(route.getMinimumDistanceMeters())
                .build();
    }
}


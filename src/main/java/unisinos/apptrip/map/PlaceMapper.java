package unisinos.apptrip.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import unisinos.apptrip.model.Place;
import unisinos.apptrip.dto.PlaceDto;
import unisinos.apptrip.repository.PostRepository;

@Component
@RequiredArgsConstructor
public class PlaceMapper {
    private final PostRepository postRepository;
	
    public PlaceDto toDto(Place place){
        return PlaceDto.builder()
        		.id(place.getId())
        		.name(place.getName())
        		.latitude(place.getLatitude())
				.longitude(place.getLongitude())
				.sequence(place.getSequence())
				.capturedAt(place.getCapturedAt())
                .clientPointId(place.getClientPointId())
                .accuracyMeters(place.getAccuracyMeters())
				.neighborhood(place.getNeighborhood())
				.street(place.getStreet())
				.streetNumber(place.getStreetNumber())
				.complement(place.getComplement())
				.city(place.getCity())
				.postalCode(place.getPostalCode())
				.country(place.getCountry())
				.state(place.getState())
				.type(place.getType())
				.routeId(place.getRoute().getId())
				.eventIds(place.getId() == null ?java.util.List.of() : postRepository.findIdsByPlaceId(place.getId()))
                .build();
    }
}


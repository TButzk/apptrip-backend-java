package unisinos.apptrip.map;

import org.springframework.stereotype.Component;

import unisinos.apptrip.model.Place;
import unisinos.apptrip.dto.PlaceDto;

@Component
public class PlaceMapper {
	
    public PlaceDto toDto(Place place){
        return PlaceDto.builder()
        		.id(place.getId())
        		.name(place.getName())
        		.latitude(place.getLatitude())
				.longitude(place.getLongitude())
				.sequence(place.getSequence())
				.capturedAt(place.getCapturedAt())
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
				.eventIds(place.getPostsIds())
                .build();
    }
}


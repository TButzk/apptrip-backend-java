package unisinos.tripverse.map;

import org.springframework.stereotype.Component;

import unisinos.tripverse.model.place.Place;
import unisinos.tripverse.model.place.PlaceDto;

@Component
public class PlaceMapper {
	
    public PlaceDto toDto(Place place){
        return PlaceDto.builder()
        		.id(place.getId())
        		.name(place.getName())
        		.latitude(place.getLatitude())
				.longitude(place.getLongitude())
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

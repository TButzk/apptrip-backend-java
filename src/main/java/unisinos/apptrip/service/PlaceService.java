package unisinos.apptrip.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisinos.apptrip.model.Location;
import unisinos.apptrip.dto.CreatePlaceDto;
import unisinos.apptrip.model.Place;
import unisinos.apptrip.dto.UpdatePlaceDto;
import unisinos.apptrip.repository.PlaceRepository;

@Service
public class PlaceService {
	
    @Autowired
    private PlaceRepository placeRepository;
    
    @Autowired
    private RouteService routeRepository;
	
    @Transactional(readOnly = true)
    public Place get(UUID id){
        return placeRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
	public Page<Place> get(int take, int skip) {
        int page = skip / take;
        return placeRepository.findAll((PageRequest.of(page, take, Sort.by("id").ascending())));
    }

    @Transactional(readOnly = true)
    public Page<Place> getByRoute(UUID routeId, int take, int skip) {
        int page = skip / take;
        var sort = Sort.by(Sort.Order.asc("sequence"), Sort.Order.asc("id"));
        return placeRepository.findAllByRouteId(routeId, PageRequest.of(page, take, sort));
    }

    @Transactional
	public Place create(CreatePlaceDto create, Location location) {
    	var route = routeRepository.get(create.getRouteId());

        double latitude = create.hasCoordinates() ? create.getLatitude() : location.getLatitude();
        double longitude = create.hasCoordinates() ? create.getLongitude() : location.getLongitude();

		var place = Place.builder()
				.name(create.getName())
                .latitude(latitude)
                .longitude(longitude)
                .sequence(create.getSequence())
                .capturedAt(create.getCapturedAt())
                .neighborhood(create.getNeighborhood())
				.street(create.getStreet())
				.streetNumber(create.getStreetNumber())
				.complement(create.getComplement())
				.city(create.getCity())
				.postalCode(create.getPostalCode())
				.country(create.getCountry())
				.state(create.getState())
				.type(create.getType())
				.route(route)
				.build();		
		
		placeRepository.save(place);
		
		return place;
	}

    @Transactional
	public Place update(UUID id, UpdatePlaceDto update) {
        var place = placeRepository.findById(id).orElseThrow();

        var name = update.getName();
        
        if(name != null && !name.isBlank())
        	place.setName(name);

        var type = update.getType();
        
        if(type != null)
        	place.setType(type);

        return placeRepository.save(place);
    }

    @Transactional
	public Place delete(UUID id) {
        var place = placeRepository.findById(id).orElseThrow();
        placeRepository.delete(place);
        return place;
    }
}


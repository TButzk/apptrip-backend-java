package unisinos.apptrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.apptrip.dto.CreatePlaceDto;
import unisinos.apptrip.dto.UpdatePlaceDto;
import unisinos.apptrip.exception.ApiException;
import unisinos.apptrip.model.Location;
import unisinos.apptrip.model.Place;
import unisinos.apptrip.model.RouteStatus;
import unisinos.apptrip.repository.PlaceRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final RouteService routeService;

    @Transactional(readOnly = true)
    public Place get(UUID id) {
        var place = find(id);
        routeService.get(place.getRoute().getId());
        return place;
    }

    @Transactional(readOnly = true)
    public Place find(UUID id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ponto não encontrado."));
    }

    @Transactional(readOnly = true)
    public Page<Place> get(int take, int skip) {
        return placeRepository.findAll(PageRequest.of(skip / Math.max(take, 1), take, Sort.by("id")));
    }

    @Transactional(readOnly = true)
    public Page<Place> getByRoute(UUID routeId, int take, int skip) {
        routeService.get(routeId);
        var sort = Sort.by(Sort.Order.asc("sequence"), Sort.Order.asc("id"));
        return placeRepository.findAllByRouteId(routeId, PageRequest.of(skip / Math.max(take, 1), take, sort));
    }

    @Transactional
    public Place create(CreatePlaceDto create, Location location) {
        if (create.getClientPointId() != null) {
            var existing = placeRepository.findByClientPointId(create.getClientPointId());
            if (existing.isPresent()) {
                if (!existing.get().getRoute().getId().equals(create.getRouteId())) {
                    throw new ApiException(HttpStatus.CONFLICT, "Identificador do ponto já utilizado.");
                }
                return existing.get();
            }
        }

        var route = routeService.find(create.getRouteId());
        routeService.requireOwner(route);
        if (route.getStatus() != RouteStatus.DRAFT) {
            throw new ApiException(HttpStatus.CONFLICT, "A rota não está mais em gravação.");
        }

        double latitude = create.hasCoordinates() ?create.getLatitude() : location.getLatitude();
        double longitude = create.hasCoordinates() ?create.getLongitude() : location.getLongitude();
        var previous = placeRepository.findFirstByRouteIdOrderBySequenceDesc(route.getId());
        if (previous.isPresent()) {
            double distance = distanceMeters(
                    previous.get().getLatitude(), previous.get().getLongitude(), latitude, longitude
            );
            if (distance < route.getMinimumDistanceMeters()) {
                return previous.get();
            }
        }

        int sequence = create.getSequence() == null
                ?previous.map(point -> point.getSequence() + 1).orElse(1)
                : create.getSequence();
        var place = Place.builder()
                .name(create.getName().trim())
                .latitude(latitude)
                .longitude(longitude)
                .sequence(sequence)
                .capturedAt(create.getCapturedAt())
                .clientPointId(create.getClientPointId())
                .accuracyMeters(create.getAccuracyMeters())
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
        return placeRepository.save(place);
    }

    @Transactional
    public Place update(UUID id, UpdatePlaceDto update) {
        var place = find(id);
        routeService.requireOwner(place.getRoute());
        if (place.getRoute().getStatus() == RouteStatus.PUBLISHED) {
            throw new ApiException(HttpStatus.CONFLICT, "Pontos de rotas publicadas não podem ser alterados.");
        }
        if (update.getName() != null && !update.getName().isBlank()) {
            place.setName(update.getName().trim());
        }
        if (update.getType() != null) {
            place.setType(update.getType());
        }
        return placeRepository.save(place);
    }

    @Transactional
    public Place delete(UUID id) {
        var place = find(id);
        routeService.requireOwner(place.getRoute());
        if (place.getRoute().getStatus() != RouteStatus.DRAFT) {
            throw new ApiException(HttpStatus.CONFLICT, "Somente pontos de rotas em gravação podem ser removidos.");
        }
        placeRepository.delete(place);
        return place;
    }

    private double distanceMeters(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6_371_000;
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);
        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2)
                + Math.cos(phi1) * Math.cos(phi2)
                * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        return earthRadius * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }
}

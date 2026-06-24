package unisinos.apptrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.apptrip.dto.CreateRouteDto;
import unisinos.apptrip.dto.UpdateRouteDto;
import unisinos.apptrip.exception.ApiException;
import unisinos.apptrip.model.Route;
import unisinos.apptrip.model.RouteStatus;
import unisinos.apptrip.repository.PlaceRepository;
import unisinos.apptrip.repository.RouteRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final PlaceRepository placeRepository;
    private final AuthorizationService authorizationService;

    @Transactional
    public Route create(CreateRouteDto create) {
        var route = Route.builder()
                .name(create.getName().trim())
                .minimumDistanceMeters(create.getMinimumDistanceMeters() == null ?25 : create.getMinimumDistanceMeters())
                .user(authorizationService.currentUser())
                .build();
        return routeRepository.save(route);
    }

    @Transactional(readOnly = true)
    public Route get(UUID id) {
        var route = find(id);
        if (route.getStatus() != RouteStatus.PUBLISHED
                && !authorizationService.isCurrentUser(route.getUser().getId())
                && !authorizationService.isAdmin()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Rota não encontrada.");
        }
        return route;
    }

    @Transactional(readOnly = true)
    public Route find(UUID id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Rota não encontrada."));
    }

    @Transactional(readOnly = true)
    public Page<Route> get(int take, int skip) {
        return getPublished(take, skip);
    }

    @Transactional(readOnly = true)
    public Page<Route> getMine(int take, int skip) {
        int page = skip / Math.max(take, 1);
        return routeRepository.findAllByUserId(
                authorizationService.currentUser().getId(),
                PageRequest.of(page, take, Sort.by("id").descending())
        );
    }

    @Transactional(readOnly = true)
    public Page<Route> getPublished(int take, int skip) {
        int page = skip / Math.max(take, 1);
        return routeRepository.findAllByStatus(
                RouteStatus.PUBLISHED,
                PageRequest.of(page, take, Sort.by("publishedAt").descending())
        );
    }

    @Transactional
    public Route update(UUID id, UpdateRouteDto update) {
        var route = find(id);
        requireOwner(route);
        if (route.getStatus() == RouteStatus.PUBLISHED) {
            throw new ApiException(HttpStatus.CONFLICT, "Rotas publicadas não podem ser renomeadas.");
        }
        if (update.getName() != null && !update.getName().isBlank()) {
            route.setName(update.getName().trim());
        }
        return routeRepository.save(route);
    }

    @Transactional
    public Route delete(UUID id) {
        var route = find(id);
        requireOwnerOrAdmin(route);
        routeRepository.delete(route);
        return route;
    }

    @Transactional
    public Route finalizeRoute(UUID id) {
        var route = find(id);
        requireOwner(route);
        if (route.getStatus() != RouteStatus.DRAFT) {
            throw new ApiException(HttpStatus.CONFLICT, "Apenas rotas em gravação podem ser finalizadas.");
        }
        route.setStatus(RouteStatus.FINISHED);
        route.setFinalizedAt(LocalDateTime.now());
        return routeRepository.save(route);
    }

    @Transactional
    public Route publish(UUID id) {
        var route = find(id);
        requireOwner(route);
        if (route.getStatus() != RouteStatus.FINISHED) {
            throw new ApiException(HttpStatus.CONFLICT, "Finalize a rota antes de publicá-la.");
        }
        if (placeRepository.countByRouteId(route.getId()) < 2) {
            throw new ApiException(HttpStatus.CONFLICT, "A rota precisa de pelo menos 2 pontos para ser publicada.");
        }
        route.setStatus(RouteStatus.PUBLISHED);
        route.setPublishedAt(LocalDateTime.now());
        return routeRepository.save(route);
    }

    public void requireOwner(Route route) {
        if (!authorizationService.isCurrentUser(route.getUser().getId())) {
            throw new ApiException(HttpStatus.FORBIDDEN, "Somente o autor da rota pode realizar esta ação.");
        }
    }

    public void requireOwnerOrAdmin(Route route) {
        authorizationService.requireOwnerOrAdmin(route.getUser().getId(), "Acesso negado à rota.");
    }
}

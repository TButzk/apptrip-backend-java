package unisinos.tripverse.service;

import java.util.UUID;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisinos.tripverse.provider.UserProvider;
import unisinos.tripverse.dto.CreateRouteDto;
import unisinos.tripverse.model.Route;
import unisinos.tripverse.model.RouteStatus;
import unisinos.tripverse.repository.PlaceRepository;
import unisinos.tripverse.repository.RouteRepository;
import unisinos.tripverse.repository.UserRepository;

@Service
public class RouteService {
	
	@Autowired
	private RouteRepository routeRepository;
	
    @Autowired
    private UserProvider userProvider;
    
    @Autowired
    private UserRepository userRepository;    

    @Autowired
    private PlaceRepository placeRepository;
	
    @Transactional
    public Route create(CreateRouteDto create){

        var auth = userProvider.getAuthenticatedUser();

        var user = userRepository.findById(auth.getId()).orElseThrow();

        var route = Route.builder()
        		.name(create.getName())
                .user(user)
                .build();

        routeRepository.save(route);

        return route;
    }

    @Transactional(readOnly = true)
    public Route get(UUID id){
        return routeRepository.findById(id).orElseThrow();
    }
    
    @Transactional(readOnly = true)
    public Page<Route> get(int take, int skip) {
        int page = skip / take;
        return routeRepository.findAll(PageRequest.of(page, take, Sort.by("id").ascending()));
    }

    @Transactional(readOnly = true)
    public Page<Route> getMine(int take, int skip) {
        int page = skip / take;
        var auth = userProvider.getAuthenticatedUser();
        return routeRepository.findAllByUserId(auth.getId(), PageRequest.of(page, take, Sort.by("id").descending()));
    }

    @Transactional(readOnly = true)
    public Page<Route> getPublished(int take, int skip) {
        int page = skip / take;
        return routeRepository.findAllByStatus(RouteStatus.PUBLISHED, PageRequest.of(page, take, Sort.by("publishedAt").descending()));
    }

    @Transactional
	public Route delete(UUID id) {
        var route = routeRepository.findById(id).orElseThrow();
        routeRepository.delete(route);
        return route;
	}

    @Transactional
    public Route publish(UUID id) {
        var route = routeRepository.findById(id).orElseThrow();
        validateOwnership(route);

        if (route.getStatus() != RouteStatus.DRAFT) {
            throw new IllegalStateException("Apenas rotas em rascunho podem ser publicadas.");
        }

        long placesCount = placeRepository.countByRouteId(route.getId());
        if (placesCount < 2) {
            throw new IllegalStateException("A rota precisa de pelo menos 2 pontos para ser publicada.");
        }

        route.setStatus(RouteStatus.PUBLISHED);
        route.setPublishedAt(LocalDateTime.now());

        return routeRepository.save(route);
    }

    @Transactional
    public Route finalizeRoute(UUID id) {
        var route = routeRepository.findById(id).orElseThrow();
        validateOwnership(route);

        if (route.getStatus() != RouteStatus.PUBLISHED) {
            throw new IllegalStateException("Apenas rotas publicadas podem ser finalizadas.");
        }

        route.setStatus(RouteStatus.FINISHED);
        route.setFinalizedAt(LocalDateTime.now());

        return routeRepository.save(route);

	}

    private void validateOwnership(Route route) {
        var auth = userProvider.getAuthenticatedUser();
        if (!route.getUser().getId().equals(auth.getId())) {
            throw new IllegalStateException("Somente o autor da rota pode alterar seu status.");
        }
    }

}

package unisinos.tripverse.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisinos.tripverse.configuration.UserProvider;
import unisinos.tripverse.model.route.CreateRouteDto;
import unisinos.tripverse.model.route.Route;
import unisinos.tripverse.repository.RouteRepository;
import unisinos.tripverse.repository.UserRepository;

@Service
public class RouteService {
	
	@Autowired
	RouteRepository routeRepository;
	
    @Autowired
    private UserProvider userProvider;
    
    @Autowired
    private UserRepository userRepository;    
	
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

    @Transactional
	public Route delete(UUID id) {
        var route = routeRepository.findById(id).orElseThrow();
        routeRepository.delete(route);
        return route;
	}    

}

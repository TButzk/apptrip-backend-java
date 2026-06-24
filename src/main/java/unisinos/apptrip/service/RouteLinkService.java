package unisinos.apptrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.apptrip.dto.RouteLinkDto;
import unisinos.apptrip.exception.ApiException;
import unisinos.apptrip.model.RouteLink;
import unisinos.apptrip.model.RouteStatus;
import unisinos.apptrip.repository.RouteLinkRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteLinkService {
    private final RouteLinkRepository routeLinkRepository;
    private final PlaceService placeService;
    private final RouteService routeService;
    private final AuthorizationService authorizationService;

    @Transactional(readOnly = true)
    public List<RouteLinkDto> list(UUID placeId) {
        placeService.get(placeId);
        return routeLinkRepository.findAllByPlaceIdOrderByCreatedAtDesc(placeId).stream().map(this::toDto).toList();
    }

    @Transactional
    public RouteLinkDto create(UUID placeId, UUID routeId) {
        var place = placeService.find(placeId);
        if (place.getRoute().getStatus() != RouteStatus.PUBLISHED) {
            throw new ApiException(HttpStatus.CONFLICT, "O ponto precisa pertencer a uma rota publicada.");
        }
        var linkedRoute = routeService.find(routeId);
        routeService.requireOwner(linkedRoute);
        if (linkedRoute.getStatus() != RouteStatus.PUBLISHED) {
            throw new ApiException(HttpStatus.CONFLICT, "A rota vinculada precisa estar publicada.");
        }
        var user = authorizationService.currentUser();
        var link = routeLinkRepository.findByPlaceIdAndLinkedRouteIdAndUserId(placeId, routeId, user.getId())
                .orElseGet(() -> routeLinkRepository.save(RouteLink.builder()
                        .place(place)
                        .linkedRoute(linkedRoute)
                        .user(user)
                        .createdAt(LocalDateTime.now())
                        .build()));
        return toDto(link);
    }

    @Transactional
    public RouteLinkDto delete(UUID id) {
        var link = routeLinkRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Vínculo não encontrado."));
        authorizationService.requireOwnerOrAdmin(link.getUser().getId(), "Somente o autor ou administrador pode remover.");
        routeLinkRepository.delete(link);
        return toDto(link);
    }

    private RouteLinkDto toDto(RouteLink link) {
        return RouteLinkDto.builder()
                .id(link.getId())
                .placeId(link.getPlace().getId())
                .routeId(link.getLinkedRoute().getId())
                .routeName(link.getLinkedRoute().getName())
                .userId(link.getUser().getId())
                .createdAt(link.getCreatedAt())
                .build();
    }
}

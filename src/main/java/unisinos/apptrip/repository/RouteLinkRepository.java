package unisinos.apptrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisinos.apptrip.model.RouteLink;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RouteLinkRepository extends JpaRepository<RouteLink, UUID> {
    List<RouteLink> findAllByPlaceIdOrderByCreatedAtDesc(UUID placeId);
    Optional<RouteLink> findByPlaceIdAndLinkedRouteIdAndUserId(UUID placeId, UUID routeId, UUID userId);
}

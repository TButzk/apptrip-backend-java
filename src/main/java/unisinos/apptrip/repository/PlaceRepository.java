package unisinos.apptrip.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unisinos.apptrip.model.Place;

import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID> {
	Page<Place> findAllByRouteId(UUID routeId, Pageable pageable);
	long countByRouteId(UUID routeId);
}


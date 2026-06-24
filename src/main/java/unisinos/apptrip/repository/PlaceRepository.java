package unisinos.apptrip.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unisinos.apptrip.model.Place;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID> {
	Page<Place> findAllByRouteId(UUID routeId, Pageable pageable);
	long countByRouteId(UUID routeId);
	Optional<Place> findByClientPointId(UUID clientPointId);
	Optional<Place> findFirstByRouteIdOrderBySequenceDesc(UUID routeId);
	List<Place> findAllByRouteIdOrderBySequenceAscIdAsc(UUID routeId);

	@Query("select p.id from Place p where p.route.id = :routeId order by p.sequence asc, p.id asc")
	List<UUID> findIdsByRouteId(UUID routeId);
}


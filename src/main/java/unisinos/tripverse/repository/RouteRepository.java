package unisinos.tripverse.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import unisinos.tripverse.model.Route;
import unisinos.tripverse.model.RouteStatus;

public interface RouteRepository extends JpaRepository<Route, UUID> {
	Page<Route> findAllByUserId(UUID userId, Pageable pageable);
	Page<Route> findAllByStatus(RouteStatus status, Pageable pageable);
}

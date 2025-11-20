package unisinos.tripverse.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import unisinos.tripverse.model.route.Route;

public interface RouteRepository extends JpaRepository<Route, UUID> {
}

package unisinos.apptrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisinos.apptrip.model.Rating;

import java.util.Optional;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {
    Optional<Rating> findByPlaceIdAndUserId(UUID placeId, UUID userId);
    long countByPlaceId(UUID placeId);

    @Query("select coalesce(avg(r.value), 0) from Rating r where r.place.id = :placeId")
    double averageByPlaceId(UUID placeId);
}

package unisinos.tripverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unisinos.tripverse.model.media.Media;

import java.util.UUID;

@Repository
public interface MediaRepository extends JpaRepository<Media, UUID> {
}

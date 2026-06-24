package unisinos.apptrip.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisinos.apptrip.model.Post;
import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    Page<Post> findAllByUserId(UUID userId, Pageable pageable);
    Page<Post> findAllByPlaceId(UUID placeId, Pageable pageable);
    List<Post> findAllByPlaceIdOrderByDateDesc(UUID placeId);

    @Query("select p.id from Post p where p.place.id = :placeId order by p.date desc, p.id asc")
    List<UUID> findIdsByPlaceId(UUID placeId);
}


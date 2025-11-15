package unisinos.tripverse.model.media;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.post.Post;

import java.util.UUID;

@Entity
@Data
@Builder
public class Media {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String name;

    private String url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private MediaType type;
}

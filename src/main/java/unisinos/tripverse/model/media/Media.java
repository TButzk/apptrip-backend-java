package unisinos.tripverse.model.media;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import unisinos.tripverse.model.post.Post;

import java.util.UUID;

@Entity
public class Media {

    @Id
    private UUID id;

    public Media(){
        id = UUID.randomUUID();
    }

    private String name;

    private String url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private MediaType type;
}

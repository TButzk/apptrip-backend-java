package unisinos.tripverse.model.media;

import jakarta.persistence.ManyToOne;
import unisinos.tripverse.model.post.Post;
import unisinos.tripverse.model.shared.EntityBase;

public class Media extends EntityBase {

    private String name;

    private String url;

    @ManyToOne
    private Post post;

    private MediaType type;
}

package unisinos.tripverse.model.comment;

import jakarta.persistence.ManyToOne;
import unisinos.tripverse.model.post.Post;
import unisinos.tripverse.model.shared.EntityBase;
import unisinos.tripverse.model.user.User;

public class Comment extends EntityBase {

    @ManyToOne
    private User user;

    private String message;

    @ManyToOne
    private Post post;
}

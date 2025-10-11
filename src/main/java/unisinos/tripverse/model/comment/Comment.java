package unisinos.tripverse.model.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import unisinos.tripverse.model.post.Post;
import unisinos.tripverse.model.user.User;

import java.util.UUID;

@Entity
public class Comment {

    @Id
    private UUID id;

    public Comment(){
        id = UUID.randomUUID();
    }

    @ManyToOne
    @JoinColumn(name =  "user_id")
    private User user;

    private String message;

    @ManyToOne
    @JoinColumn(name =  "post_id")
    private Post post;
}

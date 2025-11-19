package unisinos.tripverse.model.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unisinos.tripverse.model.post.Post;
import unisinos.tripverse.model.user.User;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name =  "user_id")
    private User user;

    private String message;

    @ManyToOne
    @JoinColumn(name =  "post_id")
    private Post post;
}

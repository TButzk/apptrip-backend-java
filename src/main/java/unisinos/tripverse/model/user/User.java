package unisinos.tripverse.model.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.comment.Comment;
import unisinos.tripverse.model.post.Post;
import unisinos.tripverse.model.route.Route;
import unisinos.tripverse.model.place.FavoritePlaces;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "app_user")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String name;

    private String password;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<FavoritePlaces> favoritePlaces;

    @OneToMany(mappedBy = "user")
    private List<Route> routes;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}

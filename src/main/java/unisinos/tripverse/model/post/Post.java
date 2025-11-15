package unisinos.tripverse.model.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.media.Media;
import unisinos.tripverse.model.place.Place;
import unisinos.tripverse.model.user.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String title;

    private String message;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Media> media;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
}

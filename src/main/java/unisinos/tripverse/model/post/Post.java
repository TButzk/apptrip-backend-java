package unisinos.tripverse.model.post;

import jakarta.persistence.*;
import unisinos.tripverse.model.media.Media;
import unisinos.tripverse.model.place.Place;
import unisinos.tripverse.model.user.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Post {

    @Id
    private UUID id;

    public Post(){
        id = UUID.randomUUID();
    }

    private String title;

    private String message;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Media> media;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
}

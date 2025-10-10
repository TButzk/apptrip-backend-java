package unisinos.tripverse.model.post;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import unisinos.tripverse.model.media.Media;
import unisinos.tripverse.model.place.Place;
import unisinos.tripverse.model.shared.EntityBase;
import unisinos.tripverse.model.user.User;

import java.util.Date;
import java.util.List;

public class Post extends EntityBase {

    private String title;

    private String message;

    private Date date;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Media> media;

    @ManyToOne
    private Place place;
}

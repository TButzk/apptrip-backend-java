package unisinos.tripverse.model.place;

import jakarta.persistence.*;
import unisinos.tripverse.model.post.Post;
import unisinos.tripverse.model.route.Route;

import java.util.List;
import java.util.UUID;

@Entity
public class Place {

    @Id
    private UUID id;

    public Place(){
        id = UUID.randomUUID();
    }

    private String name;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToMany(mappedBy = "place")
    private List<Post> posts;

    private int latitude;

    private int longitude;

    private String street;

    private String streetNumber;

    private String complement;

    private String city;

    private String postalCode;

    private String country;

    private String state;

    private PlaceType type;
}

package unisinos.tripverse.model.place;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.post.Post;
import unisinos.tripverse.model.route.Route;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
public class Place {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

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

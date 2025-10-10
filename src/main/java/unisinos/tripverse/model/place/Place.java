package unisinos.tripverse.model.place;

import jakarta.persistence.*;
import unisinos.tripverse.model.post.Post;
import unisinos.tripverse.model.route.Route;
import unisinos.tripverse.model.shared.EntityBase;

import java.util.List;

public class Place extends EntityBase {

    private String name;

    @ManyToOne
    @JoinColumn(name = "route", foreignKey = @ForeignKey(name = "fk_place_route"))
    private Route route;

    @OneToMany
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

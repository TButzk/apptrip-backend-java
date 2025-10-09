package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Place  {

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

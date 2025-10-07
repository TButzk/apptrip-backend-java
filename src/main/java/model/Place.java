package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Place  {

    private String name;

    @ManyToOne
    @JoinColumn(name = "route", foreignKey = @ForeignKey(name = "fk_place_route"))
    private Route route;

    @ManyToOne
    private Address address;

    @OneToMany
    private List<Post> posts;

    private int latitude;

    private int longitude;
}

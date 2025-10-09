package unisinos.tripverse.model.route;

import jakarta.persistence.*;
import unisinos.tripverse.model.place.Place;
import unisinos.tripverse.model.shared.EntityBase;
import unisinos.tripverse.model.user.User;

import java.util.List;

public class Route extends EntityBase {
    private String name;

    @ManyToOne
    @JoinColumn(name =  "user", foreignKey = @ForeignKey(name = "fk_user_route"))
    private User user;

    @OneToMany(mappedBy = "route")
    private List<Place> places;
}

package unisinos.tripverse.model.route;

import jakarta.persistence.*;
import unisinos.tripverse.model.place.Place;
import unisinos.tripverse.model.user.User;

import java.util.List;
import java.util.UUID;

@Entity
public class Route {
    private String name;

    @Id
    private UUID id;

    public Route(){
        id = UUID.randomUUID();
    }

    @ManyToOne
    @JoinColumn(name =  "user_id")
    private User user;

    @OneToMany(mappedBy = "route")
    private List<Place> places;
}

package unisinos.tripverse.model.user;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import unisinos.tripverse.model.route.Route;
import unisinos.tripverse.model.place.FavoritePlaces;
import unisinos.tripverse.model.shared.EntityBase;
import java.util.List;

public class User extends EntityBase {

    private String name;

    private String password;

    @ManyToMany(mappedBy = "user")
    private List<FavoritePlaces> addresses;

    @OneToMany(mappedBy = "user")
    private List<Route> route;
}

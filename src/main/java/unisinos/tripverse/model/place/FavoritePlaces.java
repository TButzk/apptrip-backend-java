package unisinos.tripverse.model.place;

import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import unisinos.tripverse.model.shared.EntityBase;
import unisinos.tripverse.model.user.User;

import java.util.List;

public class FavoritePlaces extends EntityBase {

    private String name;

    @ManyToMany()
    @JoinColumn(name =  "user", foreignKey = @ForeignKey(name = "fk_user_place"))
    private List<User> users;

    @ManyToMany()
    @JoinColumn(name =  "address", foreignKey = @ForeignKey(name = "fk_place_user"))
    private List<Place> places;
}

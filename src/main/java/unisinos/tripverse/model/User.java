package model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import model.shared.EntityBase;

import java.util.List;
import java.util.UUID;

@Entity
public class User extends EntityBase {

    private String name;

    private String password;

    private UUID address_id;

    @ManyToMany(mappedBy = "user")
    private List<FavoritePlaces> addresses;

    @OneToMany(mappedBy = "user")
    private List<Route> route;
}

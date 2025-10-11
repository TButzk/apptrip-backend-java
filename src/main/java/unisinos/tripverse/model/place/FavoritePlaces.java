package unisinos.tripverse.model.place;

import jakarta.persistence.*;
import unisinos.tripverse.model.user.User;

import java.util.List;
import java.util.UUID;

@Entity
public class FavoritePlaces {

    @Id
    private UUID id;

    public FavoritePlaces(){
        id = UUID.randomUUID();
    }

    private String name;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "place_id")
    private Place place;
}

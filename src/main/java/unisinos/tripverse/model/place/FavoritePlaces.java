package unisinos.tripverse.model.place;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.user.User;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
public class FavoritePlaces {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "place_id")
    private Place place;
}

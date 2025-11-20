package unisinos.tripverse.model.route;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unisinos.tripverse.model.place.Place;
import unisinos.tripverse.model.user.User;

import java.util.List;
import java.util.UUID;

import io.jsonwebtoken.lang.Collections;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private String name;

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name =  "user_id")
    private User user;

    @OneToMany(mappedBy = "route")
    private List<Place> places;
    
    public List<UUID> getPlaceIds() {
    	if (places == null) {
			return Collections.emptyList();
		}
    	
		return places.stream().map(Place::getId).toList();
	}    
}

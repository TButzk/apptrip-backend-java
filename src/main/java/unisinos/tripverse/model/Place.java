package unisinos.tripverse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import io.jsonwebtoken.lang.Collections;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToMany(mappedBy = "place")
    private List<Post> posts;

    private double latitude;

    private double longitude;

    private String street;

    private String streetNumber;

    private String complement;

    private String city;

    private String postalCode;

    private String country;

    private String state;

    private String neighborhood;

    private PlaceType type;
    
    public List<UUID> getPostsIds() {
    	if (posts == null) {
    		return Collections.emptyList();
		}
    	
		return posts.stream().map(Post::getId).toList();
	}
}

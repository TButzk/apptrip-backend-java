package unisinos.tripverse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RouteStatus status = RouteStatus.DRAFT;

    private LocalDateTime publishedAt;

    private LocalDateTime finalizedAt;
    
    public List<UUID> getPlaceIds() {
    	if (places == null) {
			return Collections.emptyList();
		}
    	
		return places.stream().map(Place::getId).toList();
	}    
}

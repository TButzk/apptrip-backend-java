package unisinos.tripverse.model.route;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import unisinos.tripverse.model.place.Place;
import unisinos.tripverse.model.user.User;

@Builder
@Data
public class RouteDto {
    private UUID id;	
    private String name;
    private User user;
    private List<Place> places;
}

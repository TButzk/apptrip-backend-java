package unisinos.tripverse.model.route;

import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.user.User;

@Builder
@Data
public class CreateRouteDto {	
    private String name;
}

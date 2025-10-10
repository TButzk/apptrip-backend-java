package unisinos.tripverse.model.place;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdatePlaceDTO {

    private String name;

    private PlaceType type;
}

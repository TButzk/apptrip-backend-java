package unisinos.tripverse.dto;

import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.PlaceType;

@Builder
@Data
public class UpdatePlaceDto {

    private String name;

    private PlaceType type;
}

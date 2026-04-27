package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;
import unisinos.apptrip.model.PlaceType;

@Builder
@Data
public class UpdatePlaceDto {

    private String name;

    private PlaceType type;
}


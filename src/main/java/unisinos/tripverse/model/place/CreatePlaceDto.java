package unisinos.tripverse.model.place;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatePlaceDto {

    private String name;

    private int latitude;

    private int longitude;

    private String street;

    private String streetNumber;

    private String complement;

    private String city;

    private String postalCode;

    private String country;

    private String state;

    private PlaceType type;
}

package unisinos.tripverse.model.place;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class PlaceDto {

    private UUID id;

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

    private List<UUID> eventIds;
}

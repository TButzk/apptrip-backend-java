package unisinos.tripverse.dto;

import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.PlaceType;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class PlaceDto {

    private UUID id;

    private String name;

    private double latitude;

    private double longitude;

    private String neighborhood;

    private String street;

    private String streetNumber;

    private String complement;

    private String city;

    private String postalCode;

    private String country;

    private String state;

    private PlaceType type;
    
    private UUID routeId;

    private List<UUID> eventIds;
}

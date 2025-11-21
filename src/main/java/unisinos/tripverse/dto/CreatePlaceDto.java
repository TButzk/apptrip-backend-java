package unisinos.tripverse.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.PlaceType;

@Builder
@Data
public class CreatePlaceDto {

    private String name;

    private String street;

    private String streetNumber;

    private String complement;

    private String city;

    private String postalCode;

    private String neighborhood;

    private String country;

    private String state;

    private PlaceType type;
    
    private UUID routeId;

    public String getFullAddress(){
        return
                street + " " +
                streetNumber + " " +
                neighborhood + " "+
                city + " " +
                state + " " +
                postalCode;
    }
}

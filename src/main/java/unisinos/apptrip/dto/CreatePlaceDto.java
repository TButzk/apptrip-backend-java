package unisinos.apptrip.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import unisinos.apptrip.model.PlaceType;

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

	private Double latitude;

	private Double longitude;

	private Integer sequence;

	private LocalDateTime capturedAt;
    
    private UUID routeId;

	public boolean hasCoordinates() {
		return latitude != null && longitude != null;
	}

    public String getFullAddress(){
    	
    	StringBuilder address = new StringBuilder("");
    	
    	if (street != null && !street.isEmpty()) {
    		address.append(street).append(" ");
    	}
    	
    	if (streetNumber != null && !streetNumber.isEmpty()) {
    		address.append(streetNumber).append(" ");
    	}
    	
    	if (neighborhood != null && !neighborhood.isEmpty()) {
    		address.append(neighborhood).append(" ");
    	}
    	
    	if (city != null && !city.isEmpty()) {
    		address.append(city).append(" ");
    	}
    	
    	if (state != null && !state.isEmpty()) {
    		address.append(state).append(" ");
    	}
    	
    	if (postalCode != null && !postalCode.isEmpty()) {
    		address.append(postalCode).append(" ");
    	}
    		
        return address.toString().trim();
    }
}


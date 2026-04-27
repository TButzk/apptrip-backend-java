package unisinos.apptrip.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GeocodeResponse {

    private int placeId;
    private String licence;
    private String osmType;
    private long osmId;
    private String lat;
    private String lon;
    private String type;
    private int placeRank;
    private double importance;
    private String addresstype;
    private String name;
    private String displayName;
    private Address address;

    @Builder
    @Data
    public static class Address {
        private String road;
        private String suburb;
        private String city;
        private String municipality;
        private String county;
        private String stateDistrict;
        private String state;
        private String ISO31662lvl4; // Converted from 'ISO3166-2-lvl4'
        private String region;
        private String postcode;
        private String country;
        private String countryCode; // Converted from 'country_code'
    }

}


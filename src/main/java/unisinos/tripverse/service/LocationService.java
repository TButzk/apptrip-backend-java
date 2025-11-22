package unisinos.tripverse.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import unisinos.tripverse.exception.NotFoundException;
import unisinos.tripverse.model.response.GeocodeResponse;
import unisinos.tripverse.model.Location;

import java.util.List;
import java.util.Map;

@Service
public class LocationService {

    @Value("${geocode.api-key}")
    private String apiKey;

    private final RestClient _viaCepClient;

    public LocationService(){
        _viaCepClient = RestClient.builder()
                .baseUrl("https://geocode.maps.co/search")
                .build();
    }

    public Location get(String address) throws NotFoundException {

        var map = Map.of("address", address, "apiKey", apiKey);

        var response =  _viaCepClient
                .get()
                .uri("?q={address}&api_key={apiKey}", map)
                .retrieve()
                .body(new ParameterizedTypeReference<List<GeocodeResponse>>() {});

        if(response == null)
            throw new NotFoundException("Address not found");
        if (response.isEmpty())
			throw new NotFoundException("Address not found");

        var geoResponse = response.getFirst();

        return Location.builder()
                .latitude(Double.parseDouble(geoResponse.getLat()))
                .longitude(Double.parseDouble(geoResponse.getLon()))
                .build();
    }
}

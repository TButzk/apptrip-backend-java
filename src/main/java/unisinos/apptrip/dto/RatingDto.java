package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RatingDto {
    private UUID placeId;
    private UUID userId;
    private int value;
    private double average;
    private long count;
}

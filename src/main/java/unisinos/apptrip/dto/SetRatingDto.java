package unisinos.apptrip.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class SetRatingDto {
    @Min(1)
    @Max(5)
    private int value;
}

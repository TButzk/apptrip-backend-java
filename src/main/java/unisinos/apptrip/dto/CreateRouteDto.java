package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Builder
@Data
public class CreateRouteDto {	
    @NotBlank
    @Size(max = 180)
    private String name;

    @Min(10)
    @Max(100)
    private Integer minimumDistanceMeters;
}


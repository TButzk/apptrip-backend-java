package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.Size;

@Builder
@Data
public class UpdateRouteDto {

    @Size(max = 180)
    private String name;
}

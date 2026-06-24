package unisinos.apptrip.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateRouteLinkDto {
    @NotNull
    private UUID routeId;
}

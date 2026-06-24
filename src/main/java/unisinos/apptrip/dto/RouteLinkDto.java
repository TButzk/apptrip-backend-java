package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class RouteLinkDto {
    private UUID id;
    private UUID placeId;
    private UUID routeId;
    private String routeName;
    private UUID userId;
    private LocalDateTime createdAt;
}

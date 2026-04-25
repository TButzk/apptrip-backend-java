package unisinos.tripverse.dto;

import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.RouteStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Builder
@Data
public class RouteDto {
    private UUID id;	
    private String name;
    private UUID userId;
    private List<UUID> placeIds;
    private RouteStatus status;
    private LocalDateTime publishedAt;
    private LocalDateTime finalizedAt;
}

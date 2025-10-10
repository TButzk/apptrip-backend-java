package unisinos.tripverse.model.event;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class EventDTO {
    private UUID id;

    private EventType type;

    private String name;

    private UUID placeId;
}

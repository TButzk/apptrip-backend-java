package unisinos.tripverse.model.event;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateEventDTO {

    private EventType type;

    private String name;
}

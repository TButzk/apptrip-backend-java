package unisinos.tripverse.model.event;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateEventDTO {

    private EventType type;

    private String name;

}

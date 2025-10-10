package unisinos.tripverse.model.post;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PostDTO {

    private UUID id;

    private String title;

    private String message;

    private Date date;

    private UUID userId;

    private UUID placeId;

    private UUID eventId;

    private List<UUID> mediaIds;
}

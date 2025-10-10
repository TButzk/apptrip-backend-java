package unisinos.tripverse.model.post;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Builder
@Data
public class CreatePostDTO {

    private String title;

    private String message;

    private Date date;

    private UUID userId;

    private UUID placeId;

    private Optional<UUID>  eventId;

    private List<UUID> mediaIds;
}

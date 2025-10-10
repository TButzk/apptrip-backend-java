package unisinos.tripverse.model.media;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class MediaDTO {

    private UUID id;

    private UUID postId;

    private String name;

    private String url;

}

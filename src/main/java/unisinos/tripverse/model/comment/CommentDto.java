package unisinos.tripverse.model.comment;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class CommentDto {

    private String message;

    private UUID id;

    private UUID postId;

    private UUID userId;

}

package unisinos.tripverse.model.comment;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class CreateCommentDto {

    private UUID id;

    private String message;
}

package unisinos.tripverse.model.comment;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateCommentDto {
    private String message;
}

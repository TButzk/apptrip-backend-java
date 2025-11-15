package unisinos.tripverse.model.comment;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SetCommentDto {
    private String message;
}

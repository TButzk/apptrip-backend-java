package unisinos.tripverse.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateCommentDto {
    private String message;
}

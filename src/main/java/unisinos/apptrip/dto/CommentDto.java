package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class CommentDto {

    private UUID id;

    private String message;

    private UUID postId;

    private UUID userId;

}


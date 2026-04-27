package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;
import unisinos.apptrip.model.MediaType;

import java.util.UUID;

@Builder
@Data
public class MediaDto {

    private UUID id;

    private UUID postId;

    private String name;

    private String url;

    private MediaType type;
}


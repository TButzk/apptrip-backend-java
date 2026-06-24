package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PostDto {

    private UUID id;

    private String title;

    private String message;

    private LocalDateTime date;

    private UUID userId;

    private UUID placeId;

    private List<UUID> mediaIds;

    private String userName;
}


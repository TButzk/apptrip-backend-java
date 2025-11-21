package unisinos.tripverse.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class CreatePostDto {

    private String title;

    private String message;

    private Date date;

    private UUID placeId;

    private List<UUID> mediaIds;
}

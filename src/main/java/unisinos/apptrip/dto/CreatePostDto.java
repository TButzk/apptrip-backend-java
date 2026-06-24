package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Builder
@Data
public class CreatePostDto {

    @Size(max = 180)
    private String title;

    @Size(max = 5000)
    private String message;

    private LocalDateTime date;

    @NotNull
    private UUID placeId;

    private List<UUID> mediaIds;
}


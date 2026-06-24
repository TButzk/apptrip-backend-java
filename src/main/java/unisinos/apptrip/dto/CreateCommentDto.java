package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Builder
@Data
public class CreateCommentDto {

    private UUID id;

    @NotBlank
    @Size(max = 2000)
    private String message;
}


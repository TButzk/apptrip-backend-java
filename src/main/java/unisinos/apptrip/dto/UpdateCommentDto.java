package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Builder
@Data
public class UpdateCommentDto {
    @NotBlank
    @Size(max = 2000)
    private String message;
}


package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;
import jakarta.validation.constraints.Size;

@Builder
@Data
public class UpdatePostDto {

    @Size(max = 180)
    private String title;

    @Size(max = 5000)
    private String message;

    private Optional<LocalDateTime> date;
}


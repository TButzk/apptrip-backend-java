package unisinos.tripverse.model.post;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Optional;

@Builder
@Data
public class UpdatePostDto {

    private String title;

    private String message;

    private Optional<Date> date;
}

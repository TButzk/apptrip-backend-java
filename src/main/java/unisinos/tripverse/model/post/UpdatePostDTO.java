package unisinos.tripverse.model.post;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class UpdatePostDTO {

    private String title;

    private String message;

    private Date date;
}

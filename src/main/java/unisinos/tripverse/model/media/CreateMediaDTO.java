package unisinos.tripverse.model.media;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateMediaDTO {

    private String name;

    private String url;
}

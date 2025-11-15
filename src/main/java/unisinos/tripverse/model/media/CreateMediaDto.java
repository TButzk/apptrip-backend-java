package unisinos.tripverse.model.media;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateMediaDto {

    private String name;

    private String url;
}

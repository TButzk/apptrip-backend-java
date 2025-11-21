package unisinos.tripverse.dto;

import lombok.Builder;
import lombok.Data;
import unisinos.tripverse.model.MediaType;

@Builder
@Data
public class CreateMediaDto {

    private String name;

    private String url;

    private MediaType type;
}

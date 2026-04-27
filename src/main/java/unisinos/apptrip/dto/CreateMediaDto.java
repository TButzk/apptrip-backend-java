package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;
import unisinos.apptrip.model.MediaType;

@Builder
@Data
public class CreateMediaDto {

    private String name;

    private String url;

    private MediaType type;
}


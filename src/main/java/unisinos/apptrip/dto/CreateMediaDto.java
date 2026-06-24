package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;
import unisinos.apptrip.model.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Builder
@Data
public class CreateMediaDto {

    @NotBlank
    private String name;

    @NotBlank
    private String url;

    @NotNull
    private MediaType type;

    private String storageFilename;

    private String contentType;

    private Long sizeBytes;
}


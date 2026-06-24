package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;
import unisinos.apptrip.model.MediaType;

@Data
@Builder
public class UploadDto {
    private String url;
    private String filename;
    private String originalName;
    private String contentType;
    private long sizeBytes;
    private MediaType type;
}

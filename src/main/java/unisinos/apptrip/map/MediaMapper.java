package unisinos.apptrip.map;

import org.springframework.stereotype.Component;
import unisinos.apptrip.model.Media;
import unisinos.apptrip.dto.MediaDto;

@Component
public class MediaMapper {

    public MediaDto toDto(Media media){
        return MediaDto.builder()
                .id(media.getId())
                .name(media.getName())
                .url(media.getUrl())
                .type(media.getType())
                .postId(media.getPost().getId())
                .build();
    }
}

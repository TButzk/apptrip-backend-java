package unisinos.tripverse.map;

import org.springframework.stereotype.Component;
import unisinos.tripverse.model.media.Media;
import unisinos.tripverse.model.media.MediaDto;

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
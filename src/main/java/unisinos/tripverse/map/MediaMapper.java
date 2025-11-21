package unisinos.tripverse.map;

import org.springframework.stereotype.Component;
import unisinos.tripverse.model.Media;
import unisinos.tripverse.dto.MediaDto;

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
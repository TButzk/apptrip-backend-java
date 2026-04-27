package unisinos.apptrip.map;

import org.springframework.stereotype.Component;
import unisinos.apptrip.model.Post;
import unisinos.apptrip.dto.PostDto;

@Component
public class PostMapper {

    public PostDto toDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .message(post.getMessage())
                .date(post.getDate())
                .userId(post.getUser().getId())
                .placeId(post.getPlace().getId())
                .build();
    }
}


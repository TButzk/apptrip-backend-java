package unisinos.tripverse.map;

import org.springframework.stereotype.Component;
import unisinos.tripverse.model.post.Post;
import unisinos.tripverse.model.post.PostDto;

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

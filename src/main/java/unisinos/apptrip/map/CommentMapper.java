package unisinos.apptrip.map;

import org.springframework.stereotype.Component;
import unisinos.apptrip.model.Comment;
import unisinos.apptrip.dto.CommentDto;

@Component
public class CommentMapper {

    public CommentDto toDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .message(comment.getMessage())
                .userId(comment.getUser().getId())
                .postId(comment.getPost().getId())
                .build();
    }
}


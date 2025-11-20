package unisinos.tripverse.map;

import org.springframework.stereotype.Component;
import unisinos.tripverse.model.comment.Comment;
import unisinos.tripverse.model.comment.CommentDto;

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

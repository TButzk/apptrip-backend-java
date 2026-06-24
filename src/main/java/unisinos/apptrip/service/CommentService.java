package unisinos.apptrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.apptrip.dto.CreateCommentDto;
import unisinos.apptrip.dto.UpdateCommentDto;
import unisinos.apptrip.exception.ApiException;
import unisinos.apptrip.model.Comment;
import unisinos.apptrip.model.RouteStatus;
import unisinos.apptrip.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final AuthorizationService authorizationService;

    @Transactional(readOnly = true)
    public Comment get(UUID id) {
        var comment = find(id);
        postService.get(comment.getPost().getId());
        return comment;
    }

    @Transactional(readOnly = true)
    public Comment find(UUID id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Comentário não encontrado."));
    }

    @Transactional(readOnly = true)
    public Page<Comment> get(int take, int skip) {
        return commentRepository.findAll(PageRequest.of(skip / Math.max(take, 1), take, Sort.by("createdAt")));
    }

    @Transactional(readOnly = true)
    public Page<Comment> get(UUID postId, int take, int skip) {
        postService.get(postId);
        return commentRepository.findAllByPostId(
                postId,
                PageRequest.of(skip / Math.max(take, 1), take, Sort.by("createdAt"))
        );
    }

    @Transactional
    public Comment create(UUID postId, CreateCommentDto create) {
        var post = postService.find(postId);
        if (post.getPlace().getRoute().getStatus() != RouteStatus.PUBLISHED
                && !authorizationService.isCurrentUser(post.getPlace().getRoute().getUser().getId())) {
            throw new ApiException(HttpStatus.FORBIDDEN, "Comentários exigem uma rota publicada.");
        }
        var comment = Comment.builder()
                .message(create.getMessage().trim())
                .createdAt(LocalDateTime.now())
                .user(authorizationService.currentUser())
                .post(post)
                .build();
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment update(UUID id, UpdateCommentDto update) {
        var comment = find(id);
        authorizationService.requireOwnerOrAdmin(comment.getUser().getId(), "Somente o autor ou administrador pode editar.");
        comment.setMessage(update.getMessage().trim());
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment delete(UUID id) {
        var comment = find(id);
        authorizationService.requireOwnerOrAdmin(comment.getUser().getId(), "Somente o autor ou administrador pode remover.");
        commentRepository.delete(comment);
        return comment;
    }
}

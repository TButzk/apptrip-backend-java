package unisinos.tripverse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.tripverse.provider.UserProvider;
import unisinos.tripverse.model.Comment;
import unisinos.tripverse.dto.CreateCommentDto;
import unisinos.tripverse.dto.UpdateCommentDto;
import unisinos.tripverse.repository.*;

import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Comment get(UUID id){
        return commentRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Page<Comment> get(int take, int skip) {
        int page = skip / take;
        return commentRepository.findAll(PageRequest.of(page, take, Sort.by("id").ascending()));
    }

    @Transactional(readOnly = true)
    public Page<Comment> get(UUID postId, int take, int skip) {
        int page = skip / take;
        return commentRepository.findAllByPostId(postId, PageRequest.of(page, take, Sort.by("id").ascending()));
    }

    @Transactional
    public Comment create(UUID postId, CreateCommentDto create){

        var auth = userProvider.getAuthenticatedUser();

        var user = userRepository.findById(auth.getId()).orElseThrow();

        var post = postRepository.findById(postId).orElseThrow();

        var comment = Comment.builder()
                .message(create.getMessage())
                .user(user)
                .post(post)
                .build();

        commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public Comment update(UUID id, UpdateCommentDto update){
        var comment = commentRepository.findById(id).orElseThrow();

        var message = update.getMessage();

        if(message != null && !message.isBlank())
            comment.setMessage(message);

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment delete(UUID id){
        var comment = commentRepository.findById(id).orElseThrow();
        commentRepository.delete(comment);
        return comment;
    }
}

package unisinos.tripverse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.tripverse.provider.UserProvider;
import unisinos.tripverse.dto.CreatePostDto;
import unisinos.tripverse.model.Post;
import unisinos.tripverse.dto.UpdatePostDto;
import unisinos.tripverse.repository.MediaRepository;
import unisinos.tripverse.repository.PlaceRepository;
import unisinos.tripverse.repository.PostRepository;
import unisinos.tripverse.repository.UserRepository;

import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Post get(UUID id){
        return postRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Page<Post> get(int take, int skip) {
        int page = skip / take;
        return postRepository.findAll(PageRequest.of(page, take, Sort.by("id").ascending()));
    }

    @Transactional(readOnly = true)
    public Page<Post> get(UUID userId, int take, int skip) {
        int page = skip / take;
        return postRepository.findAllByUserId(userId, PageRequest.of(page, take, Sort.by("id").ascending()));
    }

    @Transactional
    public Post create(CreatePostDto create){

        var auth = userProvider.getAuthenticatedUser();

        var user = userRepository.findById(auth.getId()).orElseThrow();

        var place = placeRepository.findById(create.getPlaceId()).orElseThrow();

        var media = mediaRepository.findAllById(create.getMediaIds());

        var post = Post.builder()
                .title(create.getTitle())
                .date(create.getDate())
                .message(create.getMessage())
                .user(user)
                .place(place)
                .media(media)
                .build();

        postRepository.save(post);

        return post;
    }

    @Transactional
    public Post update(UUID id, UpdatePostDto update){
        var post = postRepository.findById(id).orElseThrow();

        var title = update.getTitle();

        if(title != null && !title.isBlank())
            post.setTitle(title);

        var message = update.getMessage();

        if(message != null && !message.isBlank())
            post.setMessage(message);

        if(update.getDate().isPresent())
            post.setDate(update.getDate().get());

        return postRepository.save(post);
    }

    @Transactional
    public Post delete(UUID id){
        var post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return post;
    }
}

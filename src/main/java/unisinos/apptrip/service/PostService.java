package unisinos.apptrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.apptrip.dto.CreatePostDto;
import unisinos.apptrip.dto.UpdatePostDto;
import unisinos.apptrip.exception.ApiException;
import unisinos.apptrip.model.Post;
import unisinos.apptrip.model.RouteStatus;
import unisinos.apptrip.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PlaceService placeService;
    private final AuthorizationService authorizationService;

    @Transactional(readOnly = true)
    public Post get(UUID id) {
        var post = find(id);
        placeService.get(post.getPlace().getId());
        return post;
    }

    @Transactional(readOnly = true)
    public Post find(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Publicação não encontrada."));
    }

    @Transactional(readOnly = true)
    public Page<Post> get(int take, int skip) {
        return postRepository.findAll(PageRequest.of(skip / Math.max(take, 1), take, Sort.by("date").descending()));
    }

    @Transactional(readOnly = true)
    public Page<Post> get(UUID userId, int take, int skip) {
        return postRepository.findAllByUserId(
                userId,
                PageRequest.of(skip / Math.max(take, 1), take, Sort.by("date").descending())
        );
    }

    @Transactional(readOnly = true)
    public Page<Post> getByPlace(UUID placeId, int take, int skip) {
        placeService.get(placeId);
        return postRepository.findAllByPlaceId(
                placeId,
                PageRequest.of(skip / Math.max(take, 1), take, Sort.by("date").descending())
        );
    }

    @Transactional
    public Post create(CreatePostDto create) {
        var user = authorizationService.currentUser();
        var place = placeService.find(create.getPlaceId());
        var route = place.getRoute();
        boolean owner = route.getUser().getId().equals(user.getId());
        if (route.getStatus() != RouteStatus.PUBLISHED && !owner) {
            throw new ApiException(HttpStatus.FORBIDDEN, "Contribuições de terceiros exigem uma rota publicada.");
        }
        if ((create.getMessage() == null || create.getMessage().isBlank())
                && (create.getTitle() == null || create.getTitle().isBlank())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Informe um texto ou adicione mídia à publicação.");
        }
        var post = Post.builder()
                .title(trimToNull(create.getTitle()))
                .message(trimToNull(create.getMessage()))
                .date(create.getDate() == null ?LocalDateTime.now() : create.getDate())
                .user(user)
                .place(place)
                .build();
        return postRepository.save(post);
    }

    @Transactional
    public Post update(UUID id, UpdatePostDto update) {
        var post = find(id);
        authorizationService.requireOwnerOrAdmin(post.getUser().getId(), "Somente o autor ou administrador pode editar.");
        if (update.getTitle() != null) {
            post.setTitle(trimToNull(update.getTitle()));
        }
        if (update.getMessage() != null) {
            post.setMessage(trimToNull(update.getMessage()));
        }
        if (update.getDate() != null && update.getDate().isPresent()) {
            post.setDate(update.getDate().get());
        }
        return postRepository.save(post);
    }

    @Transactional
    public Post delete(UUID id) {
        var post = find(id);
        authorizationService.requireOwnerOrAdmin(post.getUser().getId(), "Somente o autor ou administrador pode remover.");
        postRepository.delete(post);
        return post;
    }

    private String trimToNull(String value) {
        return value == null || value.isBlank() ?null : value.trim();
    }
}

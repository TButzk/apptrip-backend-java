package unisinos.apptrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.apptrip.dto.CreateMediaDto;
import unisinos.apptrip.exception.ApiException;
import unisinos.apptrip.model.Media;
import unisinos.apptrip.repository.MediaRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private final PostService postService;
    private final AuthorizationService authorizationService;
    private final UploadService uploadService;

    @Transactional(readOnly = true)
    public Media get(UUID id) {
        var media = find(id);
        postService.get(media.getPost().getId());
        return media;
    }

    @Transactional(readOnly = true)
    public Media find(UUID id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Mídia não encontrada."));
    }

    @Transactional(readOnly = true)
    public Page<Media> get(UUID postId, int take, int skip) {
        postService.get(postId);
        return mediaRepository.findAllByPostId(
                postId,
                PageRequest.of(skip / Math.max(take, 1), take, Sort.by("id"))
        );
    }

    @Transactional
    public Media create(UUID postId, CreateMediaDto create) {
        var post = postService.find(postId);
        authorizationService.requireOwnerOrAdmin(post.getUser().getId(), "Somente o autor pode adicionar mídia.");
        var media = Media.builder()
                .name(create.getName().trim())
                .url(create.getUrl().trim())
                .post(post)
                .type(create.getType())
                .storageFilename(create.getStorageFilename())
                .contentType(create.getContentType())
                .sizeBytes(create.getSizeBytes())
                .build();
        return mediaRepository.save(media);
    }

    @Transactional
    public Media delete(UUID id) {
        var media = find(id);
        authorizationService.requireOwnerOrAdmin(media.getPost().getUser().getId(), "Somente o autor ou administrador pode remover.");
        mediaRepository.delete(media);
        if (media.getStorageFilename() != null) {
            uploadService.delete(media.getStorageFilename());
        }
        return media;
    }
}

package unisinos.tripverse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.tripverse.dto.CreateMediaDto;
import unisinos.tripverse.model.Media;
import unisinos.tripverse.repository.MediaRepository;
import unisinos.tripverse.repository.PostRepository;

import java.util.UUID;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional(readOnly = true)
    public Media get(UUID id){
        return mediaRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Page<Media> get(UUID postId, int take, int skip) {
        int page = skip / take;
        return mediaRepository.findAllByPostId(postId, PageRequest.of(page, take, Sort.by("id").ascending()));
    }

    @Transactional
    public Media create(UUID postId, CreateMediaDto create){

        var post = postRepository.findById(postId).orElseThrow();

        var media = Media.builder()
                .name(create.getName())
                .url(create.getUrl())
                .post(post)
                .type(create.getType())
                .build();

        mediaRepository.save(media);

        return media;
    }

    @Transactional
    public Media delete(UUID id){
        var media = mediaRepository.findById(id).orElseThrow();
        mediaRepository.delete(media);
        return media;
    }
}
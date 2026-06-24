package unisinos.apptrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.apptrip.dto.PlaceSocialDto;
import unisinos.apptrip.map.CommentMapper;
import unisinos.apptrip.map.MediaMapper;
import unisinos.apptrip.map.PlaceMapper;
import unisinos.apptrip.map.PostMapper;
import unisinos.apptrip.repository.*;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceSocialService {
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;
    private final PostMapper postMapper;
    private final MediaMapper mediaMapper;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final MediaRepository mediaRepository;
    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;
    private final RouteLinkService routeLinkService;
    private final AuthorizationService authorizationService;

    @Transactional(readOnly = true)
    public PlaceSocialDto get(UUID placeId) {
        var place = placeService.get(placeId);
        var mine = authorizationService.currentUserOptional()
                .flatMap(user -> ratingRepository.findByPlaceIdAndUserId(placeId, user.getId()))
                .map(rating -> rating.getValue())
                .orElse(null);
        return PlaceSocialDto.builder()
                .place(placeMapper.toDto(place))
                .posts(postRepository.findAllByPlaceIdOrderByDateDesc(placeId).stream().map(postMapper::toDto).toList())
                .media(mediaRepository.findAllByPostPlaceId(placeId).stream().map(mediaMapper::toDto).toList())
                .comments(commentRepository.findAllByPostPlaceIdOrderByCreatedAtAsc(placeId).stream()
                        .map(commentMapper::toDto).toList())
                .linkedRoutes(routeLinkService.list(placeId))
                .ratingAverage(ratingRepository.averageByPlaceId(placeId))
                .ratingCount(ratingRepository.countByPlaceId(placeId))
                .myRating(mine)
                .build();
    }
}

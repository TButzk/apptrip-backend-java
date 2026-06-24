package unisinos.apptrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.apptrip.dto.RatingDto;
import unisinos.apptrip.exception.ApiException;
import unisinos.apptrip.model.Rating;
import unisinos.apptrip.model.RouteStatus;
import unisinos.apptrip.repository.RatingRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final PlaceService placeService;
    private final AuthorizationService authorizationService;

    @Transactional(readOnly = true)
    public RatingDto summary(UUID placeId) {
        placeService.get(placeId);
        Integer mine = authorizationService.isAdmin() || authorizationService.currentUserOptional().isPresent()
                ?authorizationService.currentUserOptional()
                    .flatMap(user -> ratingRepository.findByPlaceIdAndUserId(placeId, user.getId()))
                    .map(Rating::getValue)
                    .orElse(null)
                : null;
        return dto(placeId, mine);
    }

    @Transactional
    public RatingDto set(UUID placeId, int value) {
        var place = placeService.find(placeId);
        if (place.getRoute().getStatus() != RouteStatus.PUBLISHED) {
            throw new ApiException(HttpStatus.CONFLICT, "Apenas pontos de rotas publicadas podem ser avaliados.");
        }
        var user = authorizationService.currentUser();
        var rating = ratingRepository.findByPlaceIdAndUserId(placeId, user.getId())
                .orElseGet(() -> Rating.builder().place(place).user(user).build());
        rating.setValue(value);
        rating.setUpdatedAt(LocalDateTime.now());
        ratingRepository.save(rating);
        return dto(placeId, value);
    }

    @Transactional
    public RatingDto delete(UUID placeId) {
        var user = authorizationService.currentUser();
        ratingRepository.findByPlaceIdAndUserId(placeId, user.getId()).ifPresent(ratingRepository::delete);
        return dto(placeId, null);
    }

    private RatingDto dto(UUID placeId, Integer mine) {
        return RatingDto.builder()
                .placeId(placeId)
                .userId(authorizationService.currentUserOptional().map(user -> user.getId()).orElse(null))
                .value(mine == null ?0 : mine)
                .average(ratingRepository.averageByPlaceId(placeId))
                .count(ratingRepository.countByPlaceId(placeId))
                .build();
    }
}

package unisinos.apptrip.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisinos.apptrip.dto.*;
import unisinos.apptrip.map.PostMapper;
import unisinos.apptrip.model.shared.DtoResponse;
import unisinos.apptrip.model.shared.PageInfo;
import unisinos.apptrip.model.shared.PageResponse;
import unisinos.apptrip.service.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PlaceSocialController {
    private final PlaceSocialService placeSocialService;
    private final PostService postService;
    private final PostMapper postMapper;
    private final RatingService ratingService;
    private final RouteLinkService routeLinkService;

    @GetMapping("api/v1/places/{placeId}/social")
    public ResponseEntity<DtoResponse<PlaceSocialDto>> social(@PathVariable UUID placeId) {
        return ResponseEntity.ok(DtoResponse.success(placeSocialService.get(placeId)));
    }

    @GetMapping("api/v1/places/{placeId}/posts")
    public ResponseEntity<PageResponse<PostDto>> posts(
            @PathVariable UUID placeId,
            @RequestParam(defaultValue = "0") int skip,
            @RequestParam(defaultValue = "50") int take
    ) {
        var page = postService.getByPlace(placeId, take, skip);
        return ResponseEntity.ok(PageResponse.success(page.map(postMapper::toDto).toList(), PageInfo.fromPage(page)));
    }

    @GetMapping("api/v1/places/{placeId}/ratings")
    public ResponseEntity<DtoResponse<RatingDto>> rating(@PathVariable UUID placeId) {
        return ResponseEntity.ok(DtoResponse.success(ratingService.summary(placeId)));
    }

    @PutMapping("api/v1/places/{placeId}/ratings/me")
    public ResponseEntity<DtoResponse<RatingDto>> rate(
            @PathVariable UUID placeId,
            @Valid @RequestBody SetRatingDto request
    ) {
        return ResponseEntity.ok(DtoResponse.success(ratingService.set(placeId, request.getValue())));
    }

    @DeleteMapping("api/v1/places/{placeId}/ratings/me")
    public ResponseEntity<DtoResponse<RatingDto>> removeRating(@PathVariable UUID placeId) {
        return ResponseEntity.ok(DtoResponse.success(ratingService.delete(placeId)));
    }

    @GetMapping("api/v1/places/{placeId}/route-links")
    public ResponseEntity<DtoResponse<java.util.List<RouteLinkDto>>> links(@PathVariable UUID placeId) {
        return ResponseEntity.ok(DtoResponse.success(routeLinkService.list(placeId)));
    }

    @PostMapping("api/v1/places/{placeId}/route-links")
    public ResponseEntity<DtoResponse<RouteLinkDto>> link(
            @PathVariable UUID placeId,
            @Valid @RequestBody CreateRouteLinkDto request
    ) {
        return ResponseEntity.ok(DtoResponse.success(routeLinkService.create(placeId, request.getRouteId())));
    }

    @DeleteMapping("api/v1/route-links/{id}")
    public ResponseEntity<DtoResponse<RouteLinkDto>> unlink(@PathVariable UUID id) {
        return ResponseEntity.ok(DtoResponse.success(routeLinkService.delete(id)));
    }
}

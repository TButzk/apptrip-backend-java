package unisinos.apptrip.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlaceSocialDto {
    private PlaceDto place;
    private List<PostDto> posts;
    private List<MediaDto> media;
    private List<CommentDto> comments;
    private List<RouteLinkDto> linkedRoutes;
    private double ratingAverage;
    private long ratingCount;
    private Integer myRating;
}

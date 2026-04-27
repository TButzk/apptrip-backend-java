package unisinos.apptrip.model.shared;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PageResponse<T> {

    private List<T> data;

    private PageInfo page;

    private String error;

    public static <T> PageResponse<T> success(List<T> parsedData, PageInfo pageInfo) {
        return PageResponse.<T>builder()
                .page(pageInfo)
                .data(parsedData)
                .build();
    }

    public static <T> PageResponse<T> error(String message) {
        return PageResponse.<T>builder()
                .error(message).build();
    }

}


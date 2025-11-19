package unisinos.tripverse.model.shared;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DtoResponse<T> {

    private T data;

    private String error;

    public static <T> DtoResponse<T> success(T data) {
        return DtoResponse.<T>builder()
                .data(data)
                .build();
    }

    public static <T> DtoResponse<T> error(String message) {
        return DtoResponse.<T>builder()
                .error(message).build();
    }
}

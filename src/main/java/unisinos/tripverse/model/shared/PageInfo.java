package unisinos.tripverse.model.shared;

import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public class PageInfo {
    private int totalPages;

    private long totalElements;

    public static PageInfo fromPage(Page page){
        return PageInfo.builder()
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}

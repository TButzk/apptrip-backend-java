package unisinos.apptrip.model.shared;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Builder
@Data
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


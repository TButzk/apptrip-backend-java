package unisinos.tripverse.model.shared;

import lombok.Data;

@Data
public abstract class SoftDeleteEntityBase {
    private Boolean deleted;
}

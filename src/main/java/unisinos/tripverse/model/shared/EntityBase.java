package unisinos.tripverse.model.shared;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public abstract class EntityBase {

    @Id
    private UUID id;

    public EntityBase(){
        id = UUID.randomUUID();
    }
}

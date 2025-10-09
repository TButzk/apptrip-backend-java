package model.shared;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data

public abstract class EntityBase {

    @Id
    private UUID Id;

    public EntityBase(){
        Id = UUID.randomUUID();
    }
}

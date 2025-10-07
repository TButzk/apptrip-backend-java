package model;

import jakarta.persistence.*;
import model.shared.EntityBase;

import java.util.List;

@Entity
public class Route extends EntityBase {
    private String name;

    @ManyToOne
    @JoinColumn(name =  "user", foreignKey = @ForeignKey(name = "fk_user_route"))
    private User user;

    @OneToMany(mappedBy = "route")
    private List<Place> places;
}

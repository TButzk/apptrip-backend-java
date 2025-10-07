package model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import model.shared.EntityBase;

@Entity
public class Comment extends EntityBase {

    @ManyToOne
    private User user;

    private String message;

    @ManyToOne
    private Post post;
}

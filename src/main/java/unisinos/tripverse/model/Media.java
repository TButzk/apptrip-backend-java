package model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Media {

    private String name;

    private String url;

    @ManyToOne
    private Post post;

    private MediaType type;
}

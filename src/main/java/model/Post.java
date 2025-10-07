package model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Post {

    private String title;

    private String message;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Media> media;

    @ManyToOne
    private Place place;

    @ManyToOne
    private Event event;
}

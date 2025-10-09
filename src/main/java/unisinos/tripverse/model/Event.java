package model;

import jakarta.persistence.ManyToOne;

public class Event {

    private EventType type;

    @ManyToOne
    private Place place;
}

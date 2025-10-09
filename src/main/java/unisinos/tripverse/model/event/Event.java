package unisinos.tripverse.model.event;

import jakarta.persistence.ManyToOne;
import unisinos.tripverse.model.place.Place;

public class Event {

    private EventType type;

    @ManyToOne
    private Place place;
}

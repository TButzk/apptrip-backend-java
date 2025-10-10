package unisinos.tripverse.model.event;

import jakarta.persistence.ManyToOne;
import unisinos.tripverse.model.place.Place;
import unisinos.tripverse.model.shared.EntityBase;

public class Event extends EntityBase {

    private EventType type;

    private String name;

    @ManyToOne
    private Place place;
}

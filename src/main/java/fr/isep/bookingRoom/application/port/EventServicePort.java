package fr.isep.bookingRoom.application.port;

import fr.isep.bookingRoom.domain.model.Event;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface EventServicePort {
    Event saveEvent(Event event) throws IOException;
    Event getEventById(String id);

    //TODO Remplacer par un DTO
    Event updateEvent(String id, Event event);
    void deleteEvent(String id);
    Page<Event> pageEvents();
}

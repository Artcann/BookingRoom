package fr.isep.bookingRoom.port;

import fr.isep.bookingRoom.domain.Event;
import org.springframework.data.domain.Page;

public interface EventServicePort {
    Event saveEvent(Event event);
    Event getEventById(String id);

    //TODO Remplacer par un DTO
    Event updateEvent(String id, Event event);
    void deleteEvent(String id);
    Page<Event> pageEvents();
}

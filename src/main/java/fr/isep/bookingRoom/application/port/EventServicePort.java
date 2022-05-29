package fr.isep.bookingRoom.application.port;

import fr.isep.bookingRoom.application.DTO.EventDto;
import fr.isep.bookingRoom.domain.model.Event;
import fr.isep.bookingRoom.domain.model.enums.EventStatusEnum;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EventServicePort {
    Event saveEvent(EventDto event);
    Event getEventById(String id);
    List<Event> getUserEventsByStatus(EventStatusEnum status);

    //TODO Remplacer par un DTO
    Event updateEvent(String id, Event event);
    void deleteEvent(String id);
    Page<Event> pageEvents();
}

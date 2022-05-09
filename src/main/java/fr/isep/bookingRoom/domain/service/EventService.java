package fr.isep.bookingRoom.domain.service;

import fr.isep.bookingRoom.domain.model.Event;
import fr.isep.bookingRoom.application.port.EventServicePort;
import fr.isep.bookingRoom.infrastructure.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventService implements EventServicePort {

    private final EventRepository eventRepository;

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getEventById(String id) {
        return eventRepository.getById(Long.valueOf(id));
    }

    @Override
    public Event updateEvent(String id, Event event) {
        return null;
    }

    @Override
    public void deleteEvent(String id) {
        Event event = eventRepository.getById(Long.valueOf(id));
        eventRepository.delete(event);
    }

    @Override
    public Page<Event> pageEvents() {
        return null;
    }
}

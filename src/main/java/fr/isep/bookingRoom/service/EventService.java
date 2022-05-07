package fr.isep.bookingRoom.service;

import fr.isep.bookingRoom.domain.Event;
import fr.isep.bookingRoom.port.EventServicePort;
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
    @Override
    public Event saveEvent(Event event) {
        return null;
    }

    @Override
    public Event getEventById(String id) {
        return null;
    }

    @Override
    public Event updateEvent(String id, Event event) {
        return null;
    }

    @Override
    public void deleteEvent(String id) {

    }

    @Override
    public Page<Event> pageEvents() {
        return null;
    }
}

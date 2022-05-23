package fr.isep.bookingRoom.domain.service;

import fr.isep.bookingRoom.application.port.EventServicePort;
import fr.isep.bookingRoom.application.port.EventTranslationServicePort;
import fr.isep.bookingRoom.domain.model.Event;
import fr.isep.bookingRoom.domain.model.EventTranslation;
import fr.isep.bookingRoom.infrastructure.adapter.TranslatorAdapter;
import fr.isep.bookingRoom.infrastructure.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventService implements EventServicePort {

    private final EventRepository eventRepository;
    private final TranslatorAdapter translatorAdapter;
    private final EventTranslationServicePort eventTranslationServicePort;

    @Override
    public Event saveEvent(Event event) throws IOException {
        String nameTranslated = this.translatorAdapter.translate("EN", event.getName());
        String descriptionTranslated = this.translatorAdapter.translate("EN", event.getDescription());
        List<EventTranslation> listEventTranslated = new ArrayList<>(Collections.singleton(this.eventTranslationServicePort.saveEventTranslation(EventTranslation.builder().name(nameTranslated).description(descriptionTranslated).lang("EN").build())));

        event.setEventTranslations(listEventTranslated);
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

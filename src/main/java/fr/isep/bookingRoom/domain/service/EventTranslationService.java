package fr.isep.bookingRoom.domain.service;

import fr.isep.bookingRoom.application.port.EventTranslationServicePort;
import fr.isep.bookingRoom.domain.model.EventTranslation;
import fr.isep.bookingRoom.infrastructure.repository.EventTranslationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventTranslationService implements EventTranslationServicePort {

    private final EventTranslationRepository eventTranslationRepository;

    @Override
    public EventTranslation saveEventTranslation(EventTranslation eventTranslation){
        return eventTranslationRepository.save(eventTranslation);
    }

    @Override
    public EventTranslation getEventTranslationById(String eventTranslationId){
        return eventTranslationRepository.getById(Long.valueOf(eventTranslationId));
    }

    @Override
    public EventTranslation updateEventTranslation(String id, EventTranslation eventTranslation){
        return null;
    }

    @Override
    public void deleteEventTranslation(String eventTranslationId){
        EventTranslation eventTranslation = eventTranslationRepository.getById(Long.valueOf(eventTranslationId));
        eventTranslationRepository.delete(eventTranslation);
    }

    @Override
    public Page<EventTranslation> getEventTranslationPaging(){
        return null;
    }
}

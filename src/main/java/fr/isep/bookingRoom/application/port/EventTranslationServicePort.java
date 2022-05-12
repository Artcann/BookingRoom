package fr.isep.bookingRoom.application.port;

import fr.isep.bookingRoom.domain.model.EventTranslation;
import org.springframework.data.domain.Page;

public interface EventTranslationServicePort {
    EventTranslation saveEventTranslation(EventTranslation eventTranslation);
    EventTranslation getEventTranslationById(String eventTranslationId);

    EventTranslation updateEventTranslation(String id, EventTranslation eventTranslation);
    Page<EventTranslation> getEventTranslationPaging();
    void deleteEventTranslation(String eventTranslationId);
}

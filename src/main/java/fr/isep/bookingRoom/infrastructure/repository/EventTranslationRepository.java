package fr.isep.bookingRoom.infrastructure.repository;

import fr.isep.bookingRoom.domain.model.EventTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTranslationRepository extends JpaRepository<EventTranslation, Long> {
    List<EventTranslation> findById(String eventId);
    List<EventTranslation> findByLang(String lang);
}

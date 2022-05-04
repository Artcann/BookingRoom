package fr.isep.bookingRoom.repository;

import fr.isep.bookingRoom.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByRoom_Label(String roomLabel);
}

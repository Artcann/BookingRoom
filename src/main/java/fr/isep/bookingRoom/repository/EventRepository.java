package fr.isep.bookingRoom.repository;

import fr.isep.bookingRoom.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByRoom_Label(String roomLabel);
}

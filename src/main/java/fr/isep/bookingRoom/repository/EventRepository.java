package fr.isep.bookingRoom.repository;

import fr.isep.bookingRoom.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepository extends JpaRepository<Event, Long> {
}
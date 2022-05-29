package fr.isep.bookingRoom.infrastructure.repository;

import fr.isep.bookingRoom.domain.model.Event;
import fr.isep.bookingRoom.domain.model.enums.EventStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByRoom_Label(String roomLabel);
    List<Event> findByUser_EmailAndStatus(String userId, EventStatusEnum eventStatusEnum);

}

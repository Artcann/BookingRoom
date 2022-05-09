package fr.isep.bookingRoom.infrastructure.repository;

import fr.isep.bookingRoom.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByLabel(String label);
    Room findByUrlId(String urlId);
}

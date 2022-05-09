package fr.isep.bookingRoom.application.port;

import fr.isep.bookingRoom.domain.model.Room;
import org.springframework.data.domain.Page;

public interface RoomServicePort {
    Room saveRoom(Room room);
    Room getRoomById(String roomId);
    Page<Room> getRoomPaging();
    Room updateRoom(String roomId, Room room);
    void deleteRoom(String roomId);
}

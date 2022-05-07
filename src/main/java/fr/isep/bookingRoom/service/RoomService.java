package fr.isep.bookingRoom.service;

import fr.isep.bookingRoom.domain.Room;
import fr.isep.bookingRoom.port.RoomServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomService implements RoomServicePort {

    @Override
    public Room saveRoom(Room room) {
        return null;
    }

    @Override
    public Room getRoomById(String roomId) {
        return null;
    }

    @Override
    public Page<Room> getRoomPaging() {
        return null;
    }

    @Override
    public Room updateRoom(String roomId, Room room) {
        return null;
    }

    @Override
    public void deleteRoom(String roomId) {

    }
}

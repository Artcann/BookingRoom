package fr.isep.bookingRoom.domain.service;

import fr.isep.bookingRoom.domain.model.Room;
import fr.isep.bookingRoom.application.port.RoomServicePort;
import fr.isep.bookingRoom.infrastructure.repository.RoomRepository;
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

    private final RoomRepository roomRepository;

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(String roomId) {
        return roomRepository.getById(Long.valueOf(roomId));
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
        Room room = this.roomRepository.getById(Long.valueOf(roomId));
        this.roomRepository.delete(room);
    }
}

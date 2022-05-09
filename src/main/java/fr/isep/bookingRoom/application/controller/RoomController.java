package fr.isep.bookingRoom.application.controller;

import fr.isep.bookingRoom.domain.model.Room;
import fr.isep.bookingRoom.application.port.RoomServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final RoomServicePort roomServicePort;

    @GetMapping("/all")
    public ResponseEntity<Page<Room>> getRooms() {
        return new ResponseEntity<>(roomServicePort.getRoomPaging(), HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(
            @PathVariable(name = "roomId") String roomId
    ) {
        return new ResponseEntity<>(roomServicePort.getRoomById(roomId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(
            @RequestBody Room room
    ) {
        return new ResponseEntity<>(roomServicePort.saveRoom(room), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{roomId}")
    public ResponseEntity<Room> updateRoom(
            @PathVariable(name = "roomId") String roomId,
            @RequestBody Room event
    ) {
        return new ResponseEntity<>(roomServicePort.updateRoom(roomId, event), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{roomId}")
    public void deleteRoom(
            @PathVariable(name = "roomId") String roomId
    ) {
        this.roomServicePort.deleteRoom(roomId);
    }
}

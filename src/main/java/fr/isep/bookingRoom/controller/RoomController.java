package fr.isep.bookingRoom.controller;

import fr.isep.bookingRoom.domain.Room;
import fr.isep.bookingRoom.port.RoomServicePort;
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

    @GetMapping("/{eventId}")
    public ResponseEntity<Room> getRoom(
            @PathVariable(name = "eventId") String eventId
    ) {
        return new ResponseEntity<>(roomServicePort.getRoomById(eventId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(
            @RequestBody Room event
    ) {
        return new ResponseEntity<>(roomServicePort.saveRoom(event), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{eventId}")
    public ResponseEntity<Room> updateRoom(
            @PathVariable(name = "eventId") String eventId,
            @RequestBody Room event
    ) {
        return new ResponseEntity<>(roomServicePort.updateRoom(eventId, event), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{eventId}")
    public void deleteRoom(
            @PathVariable(name = "eventId") String eventId
    ) {
        this.roomServicePort.deleteRoom(eventId);
    }
}

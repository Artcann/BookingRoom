package fr.isep.bookingRoom.application.controller;

import fr.isep.bookingRoom.domain.model.Event;
import fr.isep.bookingRoom.application.port.EventServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventServicePort eventServicePort;

    @GetMapping("/all")
    public ResponseEntity<Page<Event>> getEvents() {
        return new ResponseEntity<>(eventServicePort.pageEvents(), HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEvent(
            @PathVariable(name = "eventId") String eventId
    ) {
        return new ResponseEntity<>(eventServicePort.getEventById(eventId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(
            @RequestBody Event event
    ) throws IOException {
        return new ResponseEntity<>(eventServicePort.saveEvent(event), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{eventId}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable(name = "eventId") String eventId,
            @RequestBody Event event
    ) {
        return new ResponseEntity<>(eventServicePort.updateEvent(eventId, event), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(
            @PathVariable(name = "eventId") String eventId
    ) {
        this.eventServicePort.deleteEvent(eventId);
    }
}

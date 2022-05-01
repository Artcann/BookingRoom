package fr.isep.bookingRoom.controller;

import fr.isep.bookingRoom.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getEvents() {
        return null;
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEvent(
            @PathVariable(name = "eventId") String eventId
    ) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(
            @RequestBody Event event
    ) {
        return null;
    }

    @PatchMapping("/update/{eventId}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable(name = "eventId") String eventId,
            @RequestBody Event event
    ) {
        return null;
    }

    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(
            @PathVariable(name = "eventId") String eventId
    ) {
        
    }
}

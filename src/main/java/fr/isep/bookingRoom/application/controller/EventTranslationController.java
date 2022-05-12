package fr.isep.bookingRoom.application.controller;

import fr.isep.bookingRoom.application.port.EventTranslationServicePort;
import fr.isep.bookingRoom.domain.model.Event;
import fr.isep.bookingRoom.domain.model.EventTranslation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event_translation")
public class EventTranslationController {
    private final EventTranslationServicePort eventTranslationServicePort;

    @GetMapping("/all")
    public ResponseEntity<Page<EventTranslation>> getEventsTranslations(){
        return new ResponseEntity<>(eventTranslationServicePort.getEventTranslationPaging(), HttpStatus.OK);
    }

    @GetMapping("/{eventTranslationId}")
    public ResponseEntity<EventTranslation> getEventTranslation(
            @PathVariable(name = "eventTranslationId") String eventTranstionId
    ) {
        return new ResponseEntity<>(eventTranslationServicePort.getEventTranslationById(eventTranstionId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EventTranslation> createEventTranslation(
            @RequestBody EventTranslation eventTranslation
    ) {
        return new ResponseEntity<>(eventTranslationServicePort.saveEventTranslation(eventTranslation), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{eventTranslationId}")
    public ResponseEntity<EventTranslation> updateEventTranslation(
            @PathVariable(name = "eventId") String eventTranslationId,
            @RequestBody EventTranslation eventTranslation
    ) {
        return new ResponseEntity<>(eventTranslationServicePort.updateEventTranslation(eventTranslationId, eventTranslation), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{eventTranslationId}")
    public void deleteEventTranslation(
            @PathVariable(name = "eventTranslationId") String eventTranslationId
    ) {
        this.eventTranslationServicePort.deleteEventTranslation(eventTranslationId);
    }
}

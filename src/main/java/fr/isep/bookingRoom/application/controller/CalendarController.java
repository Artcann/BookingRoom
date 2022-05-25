package fr.isep.bookingRoom.application.controller;

import com.sun.istack.NotNull;
import fr.isep.bookingRoom.domain.model.Event;
import fr.isep.bookingRoom.domain.model.Week;
import fr.isep.bookingRoom.application.port.CalendarServicePort;
import lombok.RequiredArgsConstructor;
import net.fortuna.ical4j.data.ParserException;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarServicePort calendarServicePort;

    @GetMapping("/week/{roomLabel}")
    public ResponseEntity<List<Event>> getWeek(
            @PathVariable(name = "roomLabel") String roomLabel,
            @RequestParam(name = "week_start") String weekStart
    ) throws ParserException, IOException {
        return new ResponseEntity<>(calendarServicePort.getWeek(roomLabel, LocalDateTime.parse(weekStart)), HttpStatus.OK);
    }
}

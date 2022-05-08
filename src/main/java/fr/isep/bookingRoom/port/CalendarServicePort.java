package fr.isep.bookingRoom.port;

import fr.isep.bookingRoom.domain.Event;
import net.fortuna.ical4j.data.ParserException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface CalendarServicePort {
    List<Event> getWeek(String roomLabel, LocalDateTime weekStart) throws ParserException, IOException;
}

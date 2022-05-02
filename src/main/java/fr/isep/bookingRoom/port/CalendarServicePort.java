package fr.isep.bookingRoom.port;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;

public interface CalendarServicePort {
    Calendar getCalendar(String roomLabel) throws ParserException, IOException;
}

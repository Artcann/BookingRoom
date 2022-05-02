package fr.isep.bookingRoom.port;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;

public interface CalendarAdapterPort {
    Calendar getCalendar() throws IOException, ParserException;
    void outCalendar(Calendar calendar) throws IOException, ParserException;
}

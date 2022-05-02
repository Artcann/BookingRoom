package fr.isep.bookingRoom.port;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;

public interface CalendarAdapterPort {
    Calendar getIcsCalendar(String label, String URLid) throws IOException, ParserException;
    void outCalendar(Calendar calendar) throws IOException, ParserException;
}

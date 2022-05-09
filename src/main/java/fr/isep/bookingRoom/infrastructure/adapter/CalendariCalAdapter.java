package fr.isep.bookingRoom.infrastructure.adapter;

import fr.isep.bookingRoom.infrastructure.port.CalendarAdapterPort;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;


public class CalendariCalAdapter implements CalendarAdapterPort {

    public Calendar getIcsCalendar(String label, String URLid) throws IOException, ParserException {
        String url = MessageFormat.format("https://planning.isep.fr/Telechargements/ical/EdT_{0}.ics?version=13.0.2.1&idICal={1}&param=643d5b312e2e36325d2666683d3126663d31", label, URLid);
        InputStream is = new URL(url).openStream();
        Calendar calendar;
        try {
            calendar = new CalendarBuilder().build(is);
        } finally {
            is.close();
        }
        return calendar;
    }

    public void outCalendar(Calendar calendar) throws IOException {
        CalendarOutputter outputter = new CalendarOutputter();
        outputter.output(calendar, System.out);
    }
}

package fr.isep.bookingRoom.adapter;

import fr.isep.bookingRoom.port.CalendarAdapterPort;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class CalendariCalAdapter implements CalendarAdapterPort {

    public Calendar getCalendar() throws IOException, ParserException {
        InputStream is = new URL("https://planning-2122.isep.fr/Telechargements/ical/Edt_CROCHET.ics?version=2020.0.6.2&idICal=17DF63E465B97ABFE2FEA9328460A8AC&param=643d5b312e2e36325d2666683d3126663d31").openStream();
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

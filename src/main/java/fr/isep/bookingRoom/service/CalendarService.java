package fr.isep.bookingRoom.service;

import fr.isep.bookingRoom.adapter.CalendariCalAdapter;
import fr.isep.bookingRoom.domain.Room;
import fr.isep.bookingRoom.port.CalendarAdapterPort;
import fr.isep.bookingRoom.port.CalendarServicePort;
import fr.isep.bookingRoom.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class CalendarService implements CalendarServicePort {
    private final RoomRepository RoomRepository;


    @Override
    public Calendar getCalendar(String roomLabel) throws ParserException, IOException {
        System.out.println(roomLabel);
        Room room = RoomRepository.findByLabel(roomLabel);
        String URLid = room.getUrlId();
        CalendarAdapterPort calAdapter = new CalendariCalAdapter();
        Calendar calendar = calAdapter.getIcsCalendar(roomLabel, URLid);
        return calendar;
    }

    public void outCalendar(Calendar calendar) throws ParserException, IOException {
        CalendarAdapterPort calAdapter = new CalendariCalAdapter();
        calAdapter.outCalendar(calendar);
    }
}
package fr.isep.bookingRoom.service;

import fr.isep.bookingRoom.adapter.CalendariCalAdapter;
import fr.isep.bookingRoom.domain.Event;
import fr.isep.bookingRoom.domain.Room;
import fr.isep.bookingRoom.domain.enums.EventStatusEnum;
import fr.isep.bookingRoom.port.CalendarAdapterPort;
import fr.isep.bookingRoom.port.CalendarServicePort;
import fr.isep.bookingRoom.repository.EventRepository;
import fr.isep.bookingRoom.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class CalendarService implements CalendarServicePort {
    private final RoomRepository RoomRepository;
    private final EventRepository EventRepository;


    @Override
    public Calendar getCalendar(String roomLabel) throws ParserException, IOException {
        Room room = RoomRepository.findByLabel(roomLabel);
        String URLid = room.getUrlId();
        CalendarAdapterPort calAdapter = new CalendariCalAdapter();
        return calAdapter.getIcsCalendar(roomLabel, URLid);
    }

    public List<Event> getWeek(Calendar calendar, String roomLabel, LocalDateTime weekStart) {
        LocalDateTime weekEnd = weekStart.plusWeeks(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss").withZone(ZoneId.of(ZoneId.SHORT_IDS.get("ECT")));
        DateTimeFormatter dateFormatHoliday = DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneId.of(ZoneId.SHORT_IDS.get("ECT")));

        List<Event> weekEvents = new ArrayList<>();

        for(Component event: calendar.getComponents()){
            String eventStartStr = event.getProperty("DTSTART").toString();
            String eventEndStr = event.getProperty("DTEND").toString();

            eventStartStr = eventStartStr.substring(8,eventStartStr.length()-1);
            eventEndStr = eventEndStr.substring(6,eventEndStr.length()-1);

            LocalDateTime eventStart;
            LocalDateTime eventEnd;

            if(eventStartStr.contains("VALUE=DATE:")) {

                eventStartStr = eventStartStr.substring(11);
                eventEndStr = eventEndStr.substring(11);

                LocalDate eventDateStart = LocalDate.parse(
                        dateFormatHoliday.format(dateFormatHoliday.parse(eventStartStr.substring(0,8))), dateFormatHoliday);
                eventStart = LocalDateTime.of(eventDateStart, LocalTime.of(0,0));
                LocalDate eventDateEnd = LocalDate.parse(
                        dateFormatHoliday.format(dateFormatHoliday.parse(eventEndStr.substring(0,8))),dateFormatHoliday);
                eventEnd = LocalDateTime.of(eventDateEnd, LocalTime.of(23,59));
            }
            else {
                eventStart = LocalDateTime.parse(dateFormat.format(dateFormat.parse(eventStartStr.substring(0,15))),dateFormat);
                eventEnd = LocalDateTime.parse(dateFormat.format(dateFormat.parse(eventEndStr.substring(0,15))),dateFormat);
            }

            if(eventStart.isAfter(weekStart) && eventStart.isBefore(weekEnd)){
                Event eventTemp = new Event();

                eventTemp.setType("Cours");
                eventTemp.setStarting_date(eventStart);
                eventTemp.setEnding_date(eventEnd);
                eventTemp.setName(event.getProperty("SUMMARY").toString().substring(21));
                eventTemp.setStatus(EventStatusEnum.HYPERPLANNING);

                if(event.getProperty("DESCRIPTION") != null) {
                    eventTemp.setDescription(event.getProperty("DESCRIPTION").toString().substring(24));
                }
                weekEvents.add(eventTemp);
            }
        }
        List<Event> eventFromDatabase = EventRepository.findByRoom_Label(roomLabel);
        weekEvents.addAll(eventFromDatabase);
        return weekEvents;
    }

    public void outCalendar(Calendar calendar) throws ParserException, IOException {
        CalendarAdapterPort calAdapter = new CalendariCalAdapter();
        calAdapter.outCalendar(calendar);
    }
}
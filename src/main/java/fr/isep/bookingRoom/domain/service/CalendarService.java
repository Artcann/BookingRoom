package fr.isep.bookingRoom.domain.service;

import fr.isep.bookingRoom.domain.model.EventTranslation;
import fr.isep.bookingRoom.infrastructure.adapter.CalendariCalAdapter;
import fr.isep.bookingRoom.domain.model.Event;
import fr.isep.bookingRoom.domain.model.Room;
import fr.isep.bookingRoom.domain.model.enums.EventStatusEnum;
import fr.isep.bookingRoom.infrastructure.port.CalendarAdapterPort;
import fr.isep.bookingRoom.application.port.CalendarServicePort;
import fr.isep.bookingRoom.infrastructure.repository.EventRepository;
import fr.isep.bookingRoom.infrastructure.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Collection;
import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarService implements CalendarServicePort {
    private final RoomRepository RoomRepository;
    private final EventRepository EventRepository;

    private Calendar getCalendar(String roomLabel) throws ParserException, IOException {
        Room room = RoomRepository.findByLabel(roomLabel);
        String URLid = room.getUrlId();
        CalendarAdapterPort calAdapter = new CalendariCalAdapter();
        return calAdapter.getIcsCalendar(roomLabel, URLid);
    }

    public List<Event> getWeek(String roomLabel, LocalDateTime weekStart) throws ParserException, IOException {
        Calendar calendar = getCalendar(roomLabel);
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
                Collection<EventTranslation> translation = eventTemp.getEventTranslations();

                eventTemp.setType("Cours");
                eventTemp.setStarting_date(eventStart);
                eventTemp.setEnding_date(eventEnd);

                if(String.valueOf(event.getProperty("DESCRIPTION")).length() > 4) {
                    translation.add(EventTranslation.internalBuilder()
                            .name(String.valueOf(event.getProperty("SUMMARY")).substring(20))
                            .description(String.valueOf(event.getProperty("DESCRIPTION")).substring(24))
                            .lang("FR")
                            .build());
                } else {
                    translation.add(EventTranslation.internalBuilder()
                            .name(String.valueOf(event.getProperty("SUMMARY")).substring(20))
                            .description("Férié")
                            .lang("FR")
                            .build());
                }



                eventTemp.setStatus(EventStatusEnum.HYPERPLANNING);
                Collection<Room> room = new ArrayList<>();
                room.add(RoomRepository.findByLabel(roomLabel));
                eventTemp.setRoom(room);

                weekEvents.add(eventTemp);
            }
        }
        List<Event> eventFromDatabase = EventRepository.findByRoom_Label(roomLabel);
        for (Event event: eventFromDatabase) {
            if(event.getStarting_date().isAfter(weekStart) && event.getStarting_date().isBefore(weekEnd)){
                weekEvents.add(event);
            }
        }

        return weekEvents;
    }
}
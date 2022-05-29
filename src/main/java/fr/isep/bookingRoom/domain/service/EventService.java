package fr.isep.bookingRoom.domain.service;

import fr.isep.bookingRoom.application.DTO.EventDto;
import fr.isep.bookingRoom.application.port.UserServicePort;
import fr.isep.bookingRoom.domain.model.Event;
import fr.isep.bookingRoom.application.port.EventServicePort;
import fr.isep.bookingRoom.domain.model.EventTranslation;
import fr.isep.bookingRoom.domain.model.Room;
import fr.isep.bookingRoom.domain.model.enums.EventStatusEnum;
import fr.isep.bookingRoom.infrastructure.repository.EventRepository;
import fr.isep.bookingRoom.infrastructure.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventService implements EventServicePort {

    private final EventRepository eventRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final UserServicePort userServicePort;

    @Override
    public Event saveEvent(EventDto eventDto) {
        Event event = this.modelMapper.map(eventDto, Event.class);
        event.setStatus(EventStatusEnum.IN_REVIEW);
        Room room = roomRepository.findByLabel(eventDto.getRoomLabel());
        event.getRoom().add(room);

//        Collection<EventTranslation> eventTranslations = event.getEventTranslations();
//        eventTranslations.add(EventTranslation.internalBuilder()
//                .name(eventDto.getName())
//                .description(eventDto.getDescription())
//                .lang("FR")
//                .build());
//        event.setEventTranslations(eventTranslations);
        event.setUser(this.userServicePort.getProfile());

        return eventRepository.save(event);
    }

    @Override
    public Event getEventById(String id) {
        return eventRepository.getById(Long.valueOf(id));
    }

    @Override
    public List<Event> getUserEventsByStatus(EventStatusEnum status) {
        return eventRepository.findByUser_EmailAndStatus(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), status);
    }

    @Override
    public Event updateEvent(String id, Event event) {
        return null;
    }

    @Override
    public void deleteEvent(String id) {
        Event event = eventRepository.getById(Long.valueOf(id));
        eventRepository.delete(event);
    }

    @Override
    public Page<Event> pageEvents() {
        return null;
    }
}

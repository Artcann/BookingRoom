package fr.isep.bookingRoom.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Week {
    LocalDateTime weekStart;
    LocalDateTime weekEnd;
    List<Event> weekEvents;
}

package fr.isep.bookingRoom.application.DTO;

import fr.isep.bookingRoom.domain.model.EventTranslation;
import fr.isep.bookingRoom.domain.model.enums.EventStatusEnum;
import fr.isep.bookingRoom.domain.model.enums.EventTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EventDto implements Serializable {
    private Long id;
    private List<EventTranslation> eventTranslations;
    private String starting_date;
    private String ending_date;
    private String roomLabel;
    private EventTypeEnum type;
    private EventStatusEnum status;
}

package fr.isep.bookingRoom.domain.model;

import fr.isep.bookingRoom.domain.model.enums.RoomCampusEnum;
import fr.isep.bookingRoom.domain.model.enums.RoomTypeEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Enumerated(EnumType.STRING)
    private RoomCampusEnum campus;
    private Integer floor;
    @Enumerated(EnumType.STRING)
    private RoomTypeEnum type;
    private String label;

    private String urlId;
}

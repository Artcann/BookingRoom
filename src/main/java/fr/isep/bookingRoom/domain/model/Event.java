package fr.isep.bookingRoom.domain.model;

import fr.isep.bookingRoom.domain.model.enums.EventStatusEnum;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime starting_date;
    private LocalDateTime ending_date;
    private String type;
    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<Room> room = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<EventTranslation> eventTranslations = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Review> reviews = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private EventStatusEnum status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return id != null && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

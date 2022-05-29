package fr.isep.bookingRoom.domain.model;

import fr.isep.bookingRoom.domain.model.enums.EventStatusEnum;
import fr.isep.bookingRoom.domain.model.enums.EventTypeEnum;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

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
    @Column(name = "starting_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime starting_date;
    @Column(name = "ending_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime ending_date;
    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<Room> room = new HashSet<>();



    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Collection<EventTranslation> eventTranslations = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Review> reviews = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private EventStatusEnum status;

    @Enumerated(EnumType.STRING)
    private EventTypeEnum type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}, optional = false, fetch = FetchType.LAZY)
    private Userdata user;

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

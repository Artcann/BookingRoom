package fr.isep.bookingRoom.domain.model;

import fr.isep.bookingRoom.domain.model.enums.PromoEnum;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Userdata {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;

    private Integer studentId;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private PromoEnum promo;

    private String association;

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Collection<Event> events = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Userdata user = (Userdata) o;
        return id != null
                && Objects.equals(id, user.id)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

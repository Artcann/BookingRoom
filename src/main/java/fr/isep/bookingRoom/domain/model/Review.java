package fr.isep.bookingRoom.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
    private Integer grade;

    @OneToOne(cascade = CascadeType.DETACH)
    private Userdata user;
}

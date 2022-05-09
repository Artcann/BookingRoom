package fr.isep.bookingRoom.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.isep.bookingRoom.domain.model.Userdata;

public interface UserRepository extends JpaRepository<Userdata, Long> {
    Userdata findByEmail(String email);
}

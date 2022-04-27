package fr.isep.bookingRoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.isep.bookingRoom.domain.Userdata;

public interface UserRepository extends JpaRepository<Userdata, Long> {
    Userdata findByUsername(String username);
}

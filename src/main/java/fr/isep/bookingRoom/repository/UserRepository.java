package fr.isep.bookingRoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.isep.bookingRoom.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

package fr.isep.bookingRoom.repository;

import fr.isep.bookingRoom.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String rolename);
}

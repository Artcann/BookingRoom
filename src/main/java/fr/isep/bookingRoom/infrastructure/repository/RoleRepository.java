package fr.isep.bookingRoom.infrastructure.repository;

import fr.isep.bookingRoom.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String rolename);
}

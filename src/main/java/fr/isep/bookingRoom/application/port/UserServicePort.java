package fr.isep.bookingRoom.application.port;

import fr.isep.bookingRoom.domain.model.Role;
import fr.isep.bookingRoom.domain.model.Userdata;
import java.util.List;

public interface UserServicePort {
    Userdata saveUser(Userdata user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    Userdata getUser(String username);
    Userdata getProfile();

    //Utiliser Page ici
    List<Userdata> getUsers();
}

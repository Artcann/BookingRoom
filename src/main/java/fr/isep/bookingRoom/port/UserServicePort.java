package fr.isep.bookingRoom.port;

import fr.isep.bookingRoom.domain.Role;
import fr.isep.bookingRoom.domain.Userdata;
import java.util.List;

public interface UserServicePort {
    Userdata saveUser(Userdata user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    Userdata getUser(String username);

    //Utiliser Page ici
    List<Userdata> getUsers();
}

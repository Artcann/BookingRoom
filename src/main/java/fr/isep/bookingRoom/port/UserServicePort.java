package fr.isep.bookingRoom.port;

import fr.isep.bookingRoom.domain.Role;
import fr.isep.bookingRoom.domain.User;
import java.util.List;

public interface UserServicePort {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);

    //Utiliser Page ici
    List<User> getUsers();
}

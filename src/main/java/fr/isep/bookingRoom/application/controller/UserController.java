package fr.isep.bookingRoom.application.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.isep.bookingRoom.domain.model.Role;
import fr.isep.bookingRoom.domain.model.Userdata;
import fr.isep.bookingRoom.application.port.UserServicePort;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserServicePort userServicePort;
    @Value("${security.authentication.jwt.secret}")
    private String jwtSecret;

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<Userdata>> getUser() {
        return new ResponseEntity<>(userServicePort.getUsers(), HttpStatus.OK);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/save")
    public ResponseEntity<Userdata> saveUser(@RequestBody Userdata user) {
        return new ResponseEntity<>(userServicePort.saveUser(user), HttpStatus.CREATED);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return new ResponseEntity<>(userServicePort.saveRole(role), HttpStatus.CREATED);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/role/addtouser")
    public ResponseEntity<Void> saveRoleToUser(@RequestBody RoleToUserForm form) {
        userServicePort.addRoleToUser(form.getUsername(), form.getRolename());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                //TODO: utility class for Algorithm
                Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                Userdata user = userServicePort.getUser(username);

                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception ex) {
                log.error("Error sending new refresh token: {}", ex.getMessage());
                response.setHeader("error", ex.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());

                //TODO: Better error handling (sending error message is not secure)
                Map<String, String> error = new HashMap<>();
                error.put("error_message", ex.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
//TODO Transformer en DTO

@Data
class RoleToUserForm {
    private String username;
    private String rolename;
}

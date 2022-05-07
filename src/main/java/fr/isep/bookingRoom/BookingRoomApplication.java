
package fr.isep.bookingRoom;

import fr.isep.bookingRoom.domain.Event;
import fr.isep.bookingRoom.service.CalendarService;
import net.fortuna.ical4j.model.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;


@SpringBootApplication
public class BookingRoomApplication {
	@Autowired
	private CalendarService calendarService;

	public static void main(String[] args) {
		SpringApplication.run(BookingRoomApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run() {
		return args -> {
			Calendar calendar = calendarService.getCalendar("L012");
			//calendarService.outCalendar(calendar);
			//System.out.println(calendarService.getWeek(calendar, "L012", LocalDateTime.of(2022,5,2,0,0)));
			for ( Event event:
			calendarService.getWeek(calendar, "L012", LocalDateTime.of(2022,5,2,0,0))) {
				System.out.println("------------------");
				System.out.println(event);
			}
		};
	}
}
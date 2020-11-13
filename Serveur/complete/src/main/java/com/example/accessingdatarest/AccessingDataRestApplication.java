package com.example.accessingdatarest;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.accessingdatarest.Databases.EmailRepository;
import com.example.accessingdatarest.Databases.HorseRepository;
import com.example.accessingdatarest.Databases.LessonRepository;
import com.example.accessingdatarest.Databases.PersonRepository;
import com.example.accessingdatarest.Models.Email;
import com.example.accessingdatarest.Models.Horse;
import com.example.accessingdatarest.Models.Lesson;
import com.example.accessingdatarest.Models.Person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AccessingDataRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataRestApplication.class, args);

	}

	@Bean
	public CommandLineRunner demo3(HorseRepository repository) {

		return (args) -> {
			Date date = new Date(16051);

			repository.save(new Horse("Jolly Jumper", date));
			repository.save(new Horse("Black Myst", date));
			repository.save(new Horse("Tornade", date));
			repository.save(new Horse("Fusion", date));
			repository.save(new Horse("Storm", date));
		};
	}

	@Bean
	public CommandLineRunner demo(PersonRepository repository) {

		return (args) -> {
			BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
			String mama = pass.encode("password");

			repository.save(new Person("Administrateur", "ADMNISTRATEUR", "administrateur@hotmail.com", mama, "Administrateur",
			"0738434597", "*******"));

			repository.save(new Person("Jean-Bastien", "VILO", "jbvilo@hotmail.com", mama, "Administrateur",
					"0738434597", "*******"));
			repository.save(
					new Person("Emily", "FLORA", "emiflo@hotmail.com", mama, "Cavalier", "0634504597", "*******"));
			repository.save(
					new Person("Anggun", "PETI", "anggun@hotmail.com", mama, "Moniteur", "0634504596", "*******"));
					repository.save(
						new Person("Moniteur", "Moniteur", "moniteur@hotmail.com", mama, "Moniteur", "0634543596", "*******"));
		};
	}

	@Bean
	public CommandLineRunner demo4(EmailRepository repository) {

		return (args) -> {
			repository.save(new Email("ooo"));

		};
	}

	@Bean
	public CommandLineRunner demo2(LessonRepository repository) {
		Date date = new Date(16051);

		Person person = new Person("Jean-Bastien", "VILO", "jbvilo@hotmail.com", "mama", "Administrateur", "0738434597","*******");
		List<String> student = new ArrayList<String>();

		// Horse horse = new Horse("JOlly","jump");
		Set<Horse> horses = new HashSet<>();

		return (args) -> {

			repository.save(new Lesson("BALADE", "Emily FLORA", "7", date, "7", "8", student, horses, "45"));
			repository.save(new Lesson("SAUT", "Jean-Bastien VILO", "7", date, "7", "8", student, horses, "35"));
			repository.save(new Lesson("COURSE", "Jean-Bastien VILO", "7", date, "10", "15", student, horses, "25"));
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

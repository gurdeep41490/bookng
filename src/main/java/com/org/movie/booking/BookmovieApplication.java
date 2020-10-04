package com.org.movie.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.org.movie.booking.repositories")
public class BookmovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmovieApplication.class, args);
	}

}

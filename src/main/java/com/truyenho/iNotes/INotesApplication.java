package com.truyenho.iNotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.truyenho.iNotes.repository")
public class INotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(INotesApplication.class, args);
	}

}


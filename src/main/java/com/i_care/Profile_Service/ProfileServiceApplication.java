package com.i_care.Profile_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileServiceApplication {

	public static void main(String[] args) {
		System.out.println("This is profile service");
		SpringApplication.run(ProfileServiceApplication.class, args);
	}

}

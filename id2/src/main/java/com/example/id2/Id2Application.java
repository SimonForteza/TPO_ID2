package com.example.id2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
public class Id2Application {

	public static void main(String[] args) {
		SpringApplication.run(Id2Application.class, args);
	}

}

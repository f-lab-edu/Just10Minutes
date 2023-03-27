package flab.just10minutes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"flab.testController"})
public class Just10minutesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Just10minutesApplication.class, args);
	}

}

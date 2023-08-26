package br.com.spring.velocity.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.spring.velocity.example.*")
public class VelocityExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(VelocityExampleApplication.class, args);
	}
}

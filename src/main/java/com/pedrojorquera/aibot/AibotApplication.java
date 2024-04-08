package com.pedrojorquera.aibot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class AibotApplication {

	public static void main(String[] args) {
		SpringApplication.run(AibotApplication.class, args);
	}

	@Bean
	ApplicationRunner interactiveChatRunner(AiBotAgent agent) {
		return args -> {
			Scanner scanner = new Scanner(System.in);

			while (true) {
				System.out.print("User: ");
				String userMessage = scanner.nextLine();

				if ("exit".equalsIgnoreCase(userMessage)) {
					break;
				}

				String agentMessage = agent.chat(userMessage);
				System.out.println("Agent: " + agentMessage);
			}

			scanner.close();
		};
	}

}

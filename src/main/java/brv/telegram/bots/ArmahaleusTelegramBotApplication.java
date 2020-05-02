package brv.telegram.bots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableFeignClients
public class ArmahaleusTelegramBotApplication {

	public static void main(String[] args) {
		
		// Initialize bots context
		ApiContextInitializer.init();
		
		SpringApplication.run(ArmahaleusTelegramBotApplication.class, args);
	}

}

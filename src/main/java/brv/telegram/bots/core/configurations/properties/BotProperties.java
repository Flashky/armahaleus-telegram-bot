package brv.telegram.bots.core.configurations.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "bot")
public class BotProperties {

	private String username;
	private String token;
	private Integer creatorId;
	
}

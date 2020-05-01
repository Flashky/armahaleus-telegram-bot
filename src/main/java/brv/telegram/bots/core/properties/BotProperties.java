package brv.telegram.bots.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "bot")
@Data
public class BotProperties {

	private String token;
	private String name;

}

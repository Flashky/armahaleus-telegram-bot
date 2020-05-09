package brv.telegram.bots.restclients.randomcat;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "random-cat", url = "https://aws.random.cat/meow")
public interface RandomCatApiClient {

	@GetMapping
	Image getRandomCatImage();
	
}


package brv.telegram.bots.restclients.cats;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cats", url = "https://aws.random.cat/meow")
public interface CatApiClient {

	@GetMapping
	CatImage getRandomCatImage();
	
}


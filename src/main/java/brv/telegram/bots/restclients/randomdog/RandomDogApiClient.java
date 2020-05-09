package brv.telegram.bots.restclients.randomdog;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cats", url = "https://random.dog/woof.json")
public interface RandomDogApiClient {

	@GetMapping
	DogMedia getRandomDog();
}

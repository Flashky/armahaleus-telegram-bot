package brv.telegram.bots.services.dogs;

import java.util.Optional;

import brv.telegram.bots.services.common.dto.Media;

public interface DogService {

	/**
	 * Retrieves a random dog.
	 * The random dog may be a static image, gif or video.
	 * @return A Media object containing information about the retrieved dog.
	 */
	Optional<Media> getRandomDog();
	
}

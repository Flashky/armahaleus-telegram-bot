package brv.telegram.bots.services.cats;

import java.util.Optional;

import brv.telegram.bots.services.common.dto.Media;

public interface CatService {

	/**
	 * Retrieves a random cat.
	 * The random cat may be a static image, gif or video.
	 * @return A Media object containing information about the retrieved cat.
	 */
	Optional<Media> getRandomCat();
	
}

package brv.telegram.bots.services.dogs;

import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brv.telegram.bots.restclients.randomdog.DogMedia;
import brv.telegram.bots.restclients.randomdog.RandomDogApiClient;
import brv.telegram.bots.services.common.dto.Media;
import brv.telegram.bots.services.dogs.mappers.RandomDogMapper;

@Service
public class DogServiceImpl implements DogService {

	@Autowired
	private RandomDogApiClient randomDogApiClient;

	
	@Override
	public Optional<Media> getRandomDog() {
		
		DogMedia dogMedia = randomDogApiClient.getRandomDog();
		
		// Map and return the result
		RandomDogMapper mapper = Mappers.getMapper(RandomDogMapper.class);
		Media media = mapper.map(dogMedia);
		
		return Optional.ofNullable(media);
	}

}

package brv.telegram.bots.services.cats;

import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brv.telegram.bots.restclients.randomcat.Image;
import brv.telegram.bots.restclients.randomcat.RandomCatApiClient;
import brv.telegram.bots.services.cats.mappers.RandomCatMapper;
import brv.telegram.bots.services.common.dto.Media;

@Service
public class CatServiceImpl implements CatService {

	@Autowired
	private RandomCatApiClient catApiClient;

	
	@Override
	public Optional<Media> getRandomCat() {
		
		Image image = catApiClient.getRandomCatImage();
		
		// Map and return the result
		RandomCatMapper mapper = Mappers.getMapper(RandomCatMapper.class);
		Media media = mapper.map(image);

		return Optional.ofNullable(media);
	}

}

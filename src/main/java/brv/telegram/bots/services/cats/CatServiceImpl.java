package brv.telegram.bots.services.cats;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import brv.telegram.bots.restclients.catapi.SearchResult;
import brv.telegram.bots.restclients.catapi.TheCatApiClient;
import brv.telegram.bots.services.cats.mappers.SearchResultMapper;
import brv.telegram.bots.services.common.dto.Media;

@Service
public class CatServiceImpl implements CatService {

	@Autowired
	private TheCatApiClient catApiClient;

	
	@Override
	public Optional<Media> getRandomCat() {
		
		List<SearchResult> results = catApiClient.getImageSearchResults();
		
		// Map and return the result
		Media media = null;
		if(!CollectionUtils.isEmpty(results)) {
			SearchResultMapper mapper = Mappers.getMapper(SearchResultMapper.class);
			media = mapper.map(results.get(0));
		}
		
		return Optional.ofNullable(media);
	}

}

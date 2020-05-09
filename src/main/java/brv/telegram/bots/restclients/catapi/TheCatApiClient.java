package brv.telegram.bots.restclients.catapi;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "the-cat-api", url = "https://api.thecatapi.com/v1/")
public interface TheCatApiClient {
	
	@GetMapping("/images/search")
	List<SearchResult> getImageSearchResults();
	
}

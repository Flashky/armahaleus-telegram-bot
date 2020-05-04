package brv.telegram.bots.services.cats;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.io.Files;

import brv.telegram.bots.restclients.cats.CatApiClient;
import brv.telegram.bots.restclients.cats.Image;
import brv.telegram.bots.services.common.dto.Link;
import brv.telegram.bots.services.common.dto.Media;
import brv.telegram.bots.services.common.dto.MediaType;

@Service
public class CatServiceImpl implements CatService {

	@Autowired
	private CatApiClient catApiClient;
	
	
	@Override
	public Optional<Media> getRandomCat() {
		
		Image image = catApiClient.getRandomCatImage();
		
		// Map the result
		Media media = null;
		if(image != null) {
			
			media = new Media();
			String extension = Files.getFileExtension(image.getUrl());
			
			switch(extension) {
				case "gif":	
					media.setType(MediaType.GIF);
					break;
				case "mp4":
					media.setType(MediaType.VIDEO);
					break;
				default: 	
					media.setType(MediaType.IMAGE);
			}
			
			Link link = new Link();
			link.setHref(image.getUrl());
			media.setLink(link);
		}

		return Optional.ofNullable(media);
	}

}

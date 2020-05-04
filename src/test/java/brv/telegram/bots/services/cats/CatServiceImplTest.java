package brv.telegram.bots.services.cats;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import brv.telegram.bots.restclients.cats.CatApiClient;
import brv.telegram.bots.restclients.cats.Image;
import brv.telegram.bots.services.common.dto.Media;
import brv.telegram.bots.services.common.dto.MediaType;

@RunWith(MockitoJUnitRunner.class)
public class CatServiceImplTest {
	
	private final static String GIF_IMAGE_URL = "http://random-image.com/cat.gif";
	private final static String JPG_IMAGE_URL = "http://random-image.com/cat.jpg";
	private final static String PNG_IMAGE_URL = "http://random-image.com/cat.png";
	private final static String BMP_IMAGE_URL = "http://random-image.com/cat.bmp";
	private final static String MP4_VIDEO_URL = "http://random-image.com/cat.mp4";
	
	@InjectMocks
	private CatService catService = new CatServiceImpl();
	
	@Mock
	private CatApiClient catApiClient;

	@Test
	public void getRandomCatAsGifTest() {
		
		// Prepare pojos
		Image image = new Image();
		image.setUrl(GIF_IMAGE_URL);
		
		// Prepare mocks
		Mockito.doReturn(image).when(catApiClient).getRandomCatImage();
		
		// Execute test
		Optional<Media> result = catService.getRandomCat();
		
		// Assertions
		assertEquals(MediaType.GIF, result.get().getType());
		assertEquals(GIF_IMAGE_URL, result.get().getLink().getHref());
	}

	@Test
	public void getRandomCatAsMp4Test() {
		
		// Prepare pojos
		Image image = new Image();
		image.setUrl(MP4_VIDEO_URL);
		
		// Prepare mocks
		Mockito.doReturn(image).when(catApiClient).getRandomCatImage();
		
		// Execute test
		Optional<Media> result = catService.getRandomCat();
		
		// Assertions
		assertEquals(MediaType.VIDEO, result.get().getType());
		assertEquals(MP4_VIDEO_URL, result.get().getLink().getHref());
	}
	
	@Test
	public void getRandomCatAsJpgTest() {
		getRandomCatAsImageTest(JPG_IMAGE_URL);
	}

	@Test
	public void getRandomCatAsPngTest() {
		getRandomCatAsImageTest(PNG_IMAGE_URL);
	}
	
	@Test
	public void getRandomCatAsBmpTest() {
		getRandomCatAsImageTest(BMP_IMAGE_URL);
	}
	
	private void getRandomCatAsImageTest(String expectedImageUrl) {
		
		// Prepare pojos
		Image image = new Image();
		image.setUrl(expectedImageUrl);
		
		// Prepare mocks
		Mockito.doReturn(image).when(catApiClient).getRandomCatImage();
		
		// Execute test
		Optional<Media> result = catService.getRandomCat();
		
		// Assertions
		assertEquals(MediaType.IMAGE, result.get().getType());
		assertEquals(expectedImageUrl, result.get().getLink().getHref());
		
	}
}

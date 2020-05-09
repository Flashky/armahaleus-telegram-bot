package brv.telegram.bots.services.dogs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import brv.telegram.bots.restclients.randomdog.DogMedia;
import brv.telegram.bots.restclients.randomdog.RandomDogApiClient;
import brv.telegram.bots.services.common.dto.Media;
import brv.telegram.bots.services.common.dto.MediaType;

@RunWith(MockitoJUnitRunner.class)
public class DogServiceImplTest {

	private final static String GIF_IMAGE_URL = "http://random-image.com/dog.gif";
	private final static String JPG_IMAGE_URL = "http://random-image.com/dog.jpg";
	private final static String PNG_IMAGE_URL = "http://random-image.com/dog.png";
	private final static String BMP_IMAGE_URL = "http://random-image.com/dog.bmp";
	private final static String JPEG_IMAGE_URL = "http://random-image.com/dog.jpeg";
	private final static String MP4_VIDEO_URL = "http://random-image.com/dog.mp4";
	
	@InjectMocks
	private DogService dogService = new DogServiceImpl();
	
	@Mock
	private RandomDogApiClient dogApiClient;
	
	@Test
	public void getRandomCatAsGifTest() {
		
		// Execute test
		Optional<Media> result = getRandomDogAsMediaTest(GIF_IMAGE_URL);
		
		// Assertions
		assertEquals(MediaType.GIF, result.get().getType());
		assertEquals(GIF_IMAGE_URL, result.get().getLink().getHref());
	}

	@Test
	public void getRandomDogAsMp4Test() {
		
		// Execute test
		Optional<Media> result = getRandomDogAsMediaTest(MP4_VIDEO_URL);
		
		
		// Assertions
		assertEquals(MediaType.VIDEO, result.get().getType());
		assertEquals(MP4_VIDEO_URL, result.get().getLink().getHref());
	}

	@Test
	public void getRandomDogAsPngTest() {
		
		Optional<Media> result = getRandomDogAsMediaTest(PNG_IMAGE_URL);
		
		// Assertions
		assertEquals(MediaType.IMAGE, result.get().getType());
		assertEquals(PNG_IMAGE_URL, result.get().getLink().getHref());
	}
	
	@Test
	public void getRandomDogAsBmpTest() {
		
		Optional<Media> result = getRandomDogAsMediaTest(BMP_IMAGE_URL);
		
		// Assertions
		assertEquals(MediaType.IMAGE, result.get().getType());
		assertEquals(BMP_IMAGE_URL, result.get().getLink().getHref());
	}
	
	
	@Test
	public void getRandomCatAsJpgTest() {
		Optional<Media> result = getRandomDogAsMediaTest(JPG_IMAGE_URL);
		
		// Assertions
		assertEquals(MediaType.IMAGE, result.get().getType());
		assertEquals(JPG_IMAGE_URL, result.get().getLink().getHref());
	}
	
	@Test
	public void getRandomDogAsJpegTest() {
		Optional<Media> result = getRandomDogAsMediaTest(JPEG_IMAGE_URL);
		
		// Assertions
		assertEquals(MediaType.IMAGE, result.get().getType());
		assertEquals(JPEG_IMAGE_URL, result.get().getLink().getHref());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void getRandomDogNull() {
		
		// Prepare mocks
		Mockito.doReturn(null).when(dogApiClient).getRandomDog();
		
		// Execute test
		Optional<Media> result = dogService.getRandomDog();
		
		// Assertions
		assertNull(result.get());

	}
	
	private Optional<Media> getRandomDogAsMediaTest(String expectedMediaUrl) {
		
		// Prepare pojos
		DogMedia media = new DogMedia();
		media.setUrl(expectedMediaUrl);
		
		// Prepare mocks
		Mockito.doReturn(media).when(dogApiClient).getRandomDog();
		
		// Execute test
		return dogService.getRandomDog();
		
	}
}

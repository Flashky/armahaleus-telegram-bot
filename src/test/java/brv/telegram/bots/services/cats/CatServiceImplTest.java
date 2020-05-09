package brv.telegram.bots.services.cats;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import brv.telegram.bots.restclients.catapi.SearchResult;
import brv.telegram.bots.restclients.catapi.TheCatApiClient;
import brv.telegram.bots.services.common.dto.Media;
import brv.telegram.bots.services.common.dto.MediaType;

@RunWith(MockitoJUnitRunner.class)
public class CatServiceImplTest {
	
	private final static String GIF_IMAGE_URL = "http://random-image.com/cat.gif";
	private final static String JPG_IMAGE_URL = "http://random-image.com/cat.jpg";
	private final static String PNG_IMAGE_URL = "http://random-image.com/cat.png";
	private final static String BMP_IMAGE_URL = "http://random-image.com/cat.bmp";
	private final static String JPEG_IMAGE_URL = "http://random-image.com/cat.jpeg";
	private final static String MP4_VIDEO_URL = "http://random-image.com/cat.mp4";
	
	@InjectMocks
	private CatService catService = new CatServiceImpl();
	
	@Mock
	private TheCatApiClient catApiClient;

	@Test
	public void getRandomCatAsGifTest() {
		
		// Execute test
		Optional<Media> result = getRandomCatAsImageTest(GIF_IMAGE_URL);
		
		// Assertions
		assertEquals(MediaType.GIF, result.get().getType());
		assertEquals(GIF_IMAGE_URL, result.get().getLink().getHref());
	}

	@Test
	public void getRandomCatAsMp4Test() {
		
		// Execute test
		Optional<Media> result = getRandomCatAsImageTest(MP4_VIDEO_URL);
		
		
		// Assertions
		assertEquals(MediaType.VIDEO, result.get().getType());
		assertEquals(MP4_VIDEO_URL, result.get().getLink().getHref());
	}

	@Test
	public void getRandomCatAsPngTest() {
		
		Optional<Media> result = getRandomCatAsImageTest(PNG_IMAGE_URL);
		
		// Assertions
		assertEquals(MediaType.IMAGE, result.get().getType());
		assertEquals(PNG_IMAGE_URL, result.get().getLink().getHref());
	}
	
	@Test
	public void getRandomCatAsBmpTest() {
		
		Optional<Media> result = getRandomCatAsImageTest(BMP_IMAGE_URL);
		
		// Assertions
		assertEquals(MediaType.IMAGE, result.get().getType());
		assertEquals(BMP_IMAGE_URL, result.get().getLink().getHref());
	}
	
	
	@Test
	public void getRandomCatAsJpgTest() {
		Optional<Media> result = getRandomCatAsImageTest(JPG_IMAGE_URL);
		
		// Assertions
		assertEquals(MediaType.IMAGE, result.get().getType());
		assertEquals(JPG_IMAGE_URL, result.get().getLink().getHref());
	}
	
	@Test
	public void getRandomCatAsJpegTest() {
		Optional<Media> result = getRandomCatAsImageTest(JPEG_IMAGE_URL);
		
		// Assertions
		assertEquals(MediaType.IMAGE, result.get().getType());
		assertEquals(JPEG_IMAGE_URL, result.get().getLink().getHref());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void getRandomCatNull() {
		
		// Prepare mocks
		Mockito.doReturn(null).when(catApiClient).getImageSearchResults();
		
		// Execute test
		Optional<Media> result = catService.getRandomCat();
		
		// Assertions
		assertNull(result.get());

	}
	
	@Test(expected = NoSuchElementException.class)
	public void getRandomCatEmpty() {
		
		// Prepare mocks
		Mockito.doReturn(new ArrayList<>()).when(catApiClient).getImageSearchResults();
		
		// Execute test
		Optional<Media> result = catService.getRandomCat();
		
		// Assertions
		assertNull(result.get());

	}
	
	private Optional<Media> getRandomCatAsImageTest(String expectedImageUrl) {
		
		// Prepare pojos
		SearchResult image = new SearchResult();
		image.setUrl(expectedImageUrl);
		List<SearchResult> results = new ArrayList<>();
		results.add(image);
		
		// Prepare mocks
		Mockito.doReturn(results).when(catApiClient).getImageSearchResults();
		
		// Execute test
		return catService.getRandomCat();
		
	}
}

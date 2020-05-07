package brv.telegram.bots.services.common.mappers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import brv.telegram.bots.services.common.dto.MediaType;

public class RegexMediaTypeMapperTest {

	private final static String GIF_IMAGE_URL = "http://random-image.com/cat.gif";
	private final static String JPG_IMAGE_URL = "http://random-image.com/cat.jpg";
	private final static String JPEG_IMAGE_URL = "http://random-image.com/cat.jpeg";
	private final static String PNG_IMAGE_URL = "http://random-image.com/cat.png";
	private final static String BMP_IMAGE_URL = "http://random-image.com/cat.bmp";
	private final static String MP4_VIDEO_URL = "http://random-image.com/cat.mp4";
	private final static String WEBM_VIDEO_URL = "http://random-image.com/cat.webm";
	private final static String AVI_VIDEO_URL = "http://random-image.com/cat.avi";
	private final static String UNKNOWN_MEDIA_URL = "http://random-image.com/cat.xxx";
	
	private MediaTypeMapper mediaTypeGuesser = new RegexMediaTypeMapper();

	@Test
	public void testUnknownMedia() {

		MediaType result = mediaTypeGuesser.getMediaType(UNKNOWN_MEDIA_URL);
		assertEquals(MediaType.OTHER, result);
		
	}
	
	@Test
	public void testNoMedia() {

		MediaType result = mediaTypeGuesser.getMediaType("");
		assertEquals(MediaType.OTHER, result);
		
	}
	
	@Test
	public void testNullMedia() {

		MediaType result = mediaTypeGuesser.getMediaType(null);
		assertEquals(MediaType.OTHER, result);
		
	}
	
	
	@Test
	public void testEmptyMedia() {

		MediaType result = mediaTypeGuesser.getMediaType("  ");
		assertEquals(MediaType.OTHER, result);
		
	}
	
	@Test
	public void testGifImage() {

		MediaType result = mediaTypeGuesser.getMediaType(GIF_IMAGE_URL);
		assertEquals(MediaType.GIF, result);
		
	}

	@Test
	public void testJpgImage() {

		MediaType result = mediaTypeGuesser.getMediaType(JPG_IMAGE_URL);
		assertEquals(MediaType.IMAGE, result);
		
	}
	
	@Test
	public void testJpegImage() {

		MediaType result = mediaTypeGuesser.getMediaType(JPEG_IMAGE_URL);
		assertEquals(MediaType.IMAGE, result);
		
	}
	
	@Test
	public void testPngImage() {

		MediaType result = mediaTypeGuesser.getMediaType(PNG_IMAGE_URL);
		assertEquals(MediaType.IMAGE, result);
		
	}
	
	@Test
	public void testBmpImage() {

		MediaType result = mediaTypeGuesser.getMediaType(BMP_IMAGE_URL);
		assertEquals(MediaType.IMAGE, result);
		
	}
	
	@Test
	public void testMp4Video() {

		MediaType result = mediaTypeGuesser.getMediaType(MP4_VIDEO_URL);
		assertEquals(MediaType.VIDEO, result);
		
	}
	
	@Test
	public void testWebmVideo() {

		MediaType result = mediaTypeGuesser.getMediaType(WEBM_VIDEO_URL);
		assertEquals(MediaType.VIDEO, result);
		
	}
	
	@Test
	public void testAviVideo() {

		MediaType result = mediaTypeGuesser.getMediaType(AVI_VIDEO_URL);
		assertEquals(MediaType.VIDEO, result);
		
	}
}

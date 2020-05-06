package brv.telegram.bots.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import brv.telegram.bots.core.configurations.properties.BotProperties;
import brv.telegram.bots.services.cats.CatService;
import brv.telegram.bots.services.common.dto.Link;
import brv.telegram.bots.services.common.dto.Media;
import brv.telegram.bots.services.common.dto.MediaType;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class ArmahaleusBotTest {

	public static final long CHAT_ID = 1337L;
	private static final int CREATOR_ID = 23;
	
	private static PodamFactory podamFactory = new PodamFactoryImpl();
	
	private final static String GIF_IMAGE_URL = "http://random-image.com/cat.gif";
	private final static String JPG_IMAGE_URL = "http://random-image.com/cat.jpg";
	private final static String MP4_VIDEO_URL = "http://random-image.com/cat.mp4";
	
	// It is important to keep the bot static to avoid any file lock issues
	@InjectMocks
	@Spy
	private static ArmahaleusBot bot;
	
	@Mock
	private MessageSender sender;

	@Mock
	private SilentSender silent;
	
	@Mock
	private CatService catService;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		BotProperties properties = new BotProperties();
		properties.setUsername("bot-username");
		properties.setToken("bot-token");
		properties.setCreatorId(CREATOR_ID);
		
		bot = new ArmahaleusBot(properties);
	}
	@Test
	public void pawActionaAsImageTest() throws Exception{

		// Prepare context pojos
		Update update = new Update();
		User user = podamFactory.manufacturePojo(User.class);
		MessageContext context = MessageContext.newContext(update, user, CHAT_ID);

		Media media = new Media();
		media.setType(MediaType.IMAGE);
		
		Link link = new Link();
		link.setHref(JPG_IMAGE_URL);
		media.setLink(link);
		
		Optional<Media> result = Optional.of(media);
		
		// Prepare mocks
		Mockito.doReturn(result).when(catService).getRandomCat();
		
		// We consume a context in the lamda declaration, so we pass the context to the action logic
		bot.paw().action().accept(context);

		// We verify that the silent sender was called only ONCE and sent Hello World to CHAT_ID!
		Mockito.verify(sender, times(1)).sendPhoto(Mockito.any());
		Mockito.verify(silent, times(0)).send(Mockito.any(), Mockito.anyLong());
	}
	
	@Test
	public void pawActionaAsGifTest() throws Exception{

		// Prepare context pojos
		Update update = new Update();
		User user = podamFactory.manufacturePojo(User.class);
		MessageContext context = MessageContext.newContext(update, user, CHAT_ID);

		Media media = new Media();
		media.setType(MediaType.GIF);
		
		Link link = new Link();
		link.setHref(GIF_IMAGE_URL);
		media.setLink(link);
		
		Optional<Media> result = Optional.of(media);
		
		// Prepare mocks
		Mockito.doReturn(result).when(catService).getRandomCat();
		Mockito.doReturn(new Message()).when(bot).execute(Mockito.any(SendAnimation.class));

		
		// We consume a context in the lamda declaration, so we pass the context to the action logic
		bot.paw().action().accept(context);

		// We verify that the silent sender was called only ONCE and sent Hello World to CHAT_ID!
		Mockito.verify(bot, times(1)).execute(Mockito.any(SendAnimation.class));
		Mockito.verify(silent, times(0)).send(Mockito.any(), Mockito.anyLong());
	}
	
	@Test
	public void pawActionaAsVideoTest() throws Exception{

		// Prepare context pojos
		Update update = new Update();
		User user = podamFactory.manufacturePojo(User.class);
		MessageContext context = MessageContext.newContext(update, user, CHAT_ID);

		Media media = new Media();
		media.setType(MediaType.VIDEO);
		
		Link link = new Link();
		link.setHref(MP4_VIDEO_URL);
		media.setLink(link);
		
		Optional<Media> result = Optional.of(media);
		
		// Prepare mocks
		Mockito.doReturn(result).when(catService).getRandomCat();
		
		// We consume a context in the lamda declaration, so we pass the context to the action logic
		bot.paw().action().accept(context);

		// We verify that the silent sender was called only ONCE and sent Hello World to CHAT_ID!
		Mockito.verify(sender, times(1)).sendVideo(Mockito.any());
		Mockito.verify(silent, times(0)).send(Mockito.any(), Mockito.anyLong());
	}
	
	@Test
	public void pawActionOnExceptionPhotoTest() throws Exception{

		// Prepare context pojos
		MessageContext context = manufacturePojoMessageContext();
		Optional<Media> result = manufacturePojoMedia(MediaType.IMAGE, JPG_IMAGE_URL);	
		
		// Prepare mocks
		Mockito.doReturn(result).when(catService).getRandomCat();
		
		// Prepare mock to test this exception being thrown
		Mockito.doThrow(TelegramApiException.class).when(sender).sendPhoto(Mockito.any());

		// We consume a context in the lamda declaration, so we pass the context to the action logic
		bot.paw().action().accept(context);
		
		Mockito.verify(sender, times(1)).sendPhoto(Mockito.any());
		Mockito.verify(silent, times(1)).send(Mockito.any(), Mockito.anyLong());

	}
	
	@Test
	public void pawActionOnExceptionVideoTest() throws Exception{

		// Prepare context pojos
		MessageContext context = manufacturePojoMessageContext();
		Optional<Media> result = manufacturePojoMedia(MediaType.VIDEO, MP4_VIDEO_URL);		
		
		// Prepare mocks
		Mockito.doReturn(result).when(catService).getRandomCat();
		
		// Prepare mock to test this exception being thrown
		Mockito.doThrow(TelegramApiException.class).when(sender).sendVideo(Mockito.any());

		// We consume a context in the lamda declaration, so we pass the context to the action logic
		bot.paw().action().accept(context);
		
		Mockito.verify(sender, times(1)).sendVideo(Mockito.any());
		Mockito.verify(silent, times(1)).send(Mockito.any(), Mockito.anyLong());

	}
	
	@Test
	public void pawActionOnExceptionAnimationTest() throws Exception{

		// Prepare context pojos
		MessageContext context = manufacturePojoMessageContext();
		Optional<Media> result = manufacturePojoMedia(MediaType.GIF, GIF_IMAGE_URL);		
		
		// Prepare mocks
		Mockito.doReturn(result).when(catService).getRandomCat();
		
		// Prepare mock to test this exception being thrown
		Mockito.doThrow(TelegramApiException.class).when(bot).execute((SendAnimation) Mockito.any());

		// We consume a context in the lamda declaration, so we pass the context to the action logic
		bot.paw().action().accept(context);
		
		Mockito.verify(bot, times(1)).execute(Mockito.any(SendAnimation.class));
		Mockito.verify(silent, times(1)).send(Mockito.any(), Mockito.anyLong());

	}
	
	private MessageContext manufacturePojoMessageContext() {
		
		Update update = new Update();
		User user = podamFactory.manufacturePojo(User.class);
		return MessageContext.newContext(update, user, CHAT_ID);
		
	}
	
	private Optional<Media> manufacturePojoMedia(MediaType mediaType, String url) {

		Media media = new Media();
		media.setType(mediaType);
		
		Link link = new Link();
		link.setHref(url);
		media.setLink(link);
		
		return Optional.ofNullable(media);
	}
	
	@Test
	public void creatorIdTest() {
		assertEquals(CREATOR_ID, bot.creatorId());
	}

}

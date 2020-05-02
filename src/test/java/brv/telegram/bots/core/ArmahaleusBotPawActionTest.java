package brv.telegram.bots.core;

import static org.mockito.Mockito.times;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class ArmahaleusBotPawActionTest {

	public static final long CHAT_ID = 1337L;

	private PodamFactory podamFactory = new PodamFactoryImpl();
	
	// It is important to keep the bot static to avoid any file lock issues
	@InjectMocks
	private static ArmahaleusBot bot = new ArmahaleusBot("bot-token", "bot-name");
	
	@Mock
	private MessageSender sender;

	@Mock
	private SilentSender silent;
	
	@Test
	public void pawActionTest() throws Exception{

		// Prepare context pojos
		Update update = new Update();
		User user = podamFactory.manufacturePojo(User.class);
		MessageContext context = MessageContext.newContext(update, user, CHAT_ID);

		// We consume a context in the lamda declaration, so we pass the context to the action logic
		bot.paw().action().accept(context);

		// We verify that the silent sender was called only ONCE and sent Hello World to CHAT_ID!
		Mockito.verify(sender, times(1)).sendPhoto(Mockito.any());
		Mockito.verify(silent, times(0)).send(Mockito.any(), Mockito.anyLong());
	}
	
	@Test
	public void pawActionOnExceptionTest() throws Exception{

		// Prepare context pojos
		Update update = new Update();
		User user = podamFactory.manufacturePojo(User.class);
		MessageContext context = MessageContext.newContext(update, user, CHAT_ID);

		// Prepare mock to test this exception being thrown
		Mockito.doThrow(TelegramApiException.class).when(sender).sendPhoto(Mockito.any());

		// We consume a context in the lamda declaration, so we pass the context to the action logic
		bot.paw().action().accept(context);
		
		Mockito.verify(sender, times(1)).sendPhoto(Mockito.any());
		Mockito.verify(silent, times(1)).send(Mockito.any(), Mockito.anyLong());

	}

}

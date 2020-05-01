package brv.telegram.bots.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.telegram.abilitybots.api.objects.Ability;

public class ArmahaleusBotTest {

	private final static ArmahaleusBot bot = new ArmahaleusBot("bot-token", "bot-username");
	
	@Test
	public void testConstructor() {
		
		assertEquals("bot-token", bot.getBotToken());
		assertEquals("bot-username", bot.getBotUsername());
		
	}

	@Test
	public void testCreatorId() {
		
		assertEquals(0, bot.creatorId());
		
	}
	
	@Test
	public void testAbilities() {
		
		Map<String,Ability> abilities = bot.abilities();
		assertEquals(1, abilities.size());
		
	}
	
	@Test
	public void testPawAbility() {
		
		assertNotNull(bot.paw());
		
	}
}

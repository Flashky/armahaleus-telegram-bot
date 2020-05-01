package brv.telegram.bots.core;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.toggle.BareboneToggle;

public class ArmahaleusBot extends AbilityBot {
	
	private static final BareboneToggle toggle = new BareboneToggle();
	
	public ArmahaleusBot(String botToken, String botUsername) {
		super(botToken, botUsername, toggle);
	}

	@Override
	public int creatorId() {
		return 0;
	}

	
}

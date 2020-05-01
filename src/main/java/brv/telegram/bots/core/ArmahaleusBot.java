package brv.telegram.bots.core;

import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.abilitybots.api.toggle.BareboneToggle;

@Component
public class ArmahaleusBot extends AbilityBot {
	
	private static final BareboneToggle toggle = new BareboneToggle();
	
	public ArmahaleusBot(String botToken, String botUsername) {
		super(botToken, botUsername, toggle);
	}

	@Override
	public int creatorId() {
		return 0;
	}
	
	public Ability paw() {
	    return Ability.builder()
	        .name("paw") 
	        .info("Get a random kitty!")
	        .privacy(Privacy.PUBLIC)
	        .locality(Locality.ALL)
	        .input(0)
	        .action(ctx -> {
	        	System.out.println("paw paw!");
	        })
	        .build();
}


	
}

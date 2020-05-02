package brv.telegram.bots.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.abilitybots.api.toggle.BareboneToggle;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ArmahaleusBot extends AbilityBot {
	
	private static final BareboneToggle toggle = new BareboneToggle();
	
	public ArmahaleusBot(@Value("${bot.token}") String botToken, @Value("${bot.name}") String botUsername) {
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
	        .action(ctx -> this.sendRandomCatPhoto(ctx))
	        .build();
	}
	
	private void sendRandomCatPhoto(MessageContext ctx) {
		
		try {
			
			SendPhoto photo = new SendPhoto();
			photo.setChatId(ctx.chatId());
			photo.setPhoto("https://img.freepik.com/foto-gratis/gato-mojado-bano_160901-244.jpg");
	
			sender.sendPhoto(photo);
		} catch (TelegramApiException e) {
			silent.send("Sorry, I can't give you any kittens right now.", ctx.chatId());
		}
	}
	
}

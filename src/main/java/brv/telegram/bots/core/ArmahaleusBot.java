package brv.telegram.bots.core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.abilitybots.api.toggle.CustomToggle;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import brv.telegram.bots.core.configurations.properties.BotProperties;
import brv.telegram.bots.core.constants.CustomAbility;
import brv.telegram.bots.core.constants.DefaultAbility;
import brv.telegram.bots.services.cats.CatService;
import brv.telegram.bots.services.common.dto.Link;
import brv.telegram.bots.services.common.dto.Media;

@Component
public class ArmahaleusBot extends AbilityBot {
	
	private static final CustomToggle toggle = new CustomToggle()
		      .turnOff(DefaultAbility.BAN.toString())
		      .turnOff(DefaultAbility.UNBAN.toString())
		      .turnOff(DefaultAbility.PROMOTE.toString())
		      .turnOff(DefaultAbility.DEMOTE.toString());

	private int creatorId = 0;
	
	@Autowired
	private CatService catService;
	
	public ArmahaleusBot(BotProperties properties) {
		super(properties.getToken(), properties.getUsername(), toggle);
		
		if(properties.getCreatorId() != null) {
			creatorId = properties.getCreatorId();
		}
	}

	@Override
	public int creatorId() {
		return creatorId;
	}
	
	public Ability paw() {
		
		CustomAbility ability = CustomAbility.PAW;
		
	    return Ability.builder()
	        .name(ability.toString()) 
	        .info(ability.getDescription())
	        .privacy(Privacy.PUBLIC)
	        .locality(Locality.ALL)
	        .input(0)
	        .action(ctx -> this.sendRandomCatPhoto(ctx))
	        .build();
	    
	}
	
	private void sendRandomCatPhoto(MessageContext ctx) {
	
		// Query cat to service
		Optional<Media> result = catService.getRandomCat();
		
		// Send the cat to the chat
		if(result.isPresent()) {
			
			Media media = result.get();
			switch(media.getType()) {
				case GIF: 	sendAnimation(ctx, media.getLink()); break;
				case VIDEO: sendVideo(ctx, media.getLink()); break;
				default: 	sendPhoto(ctx, media.getLink()); break;
			}
		}
		
	}

	private void sendPhoto(MessageContext ctx, Link link) {
		
		SendPhoto photo = new SendPhoto();
		photo.setChatId(ctx.chatId());
		photo.setPhoto(link.getHref());
		
		send(photo);
	}

	private void sendAnimation(MessageContext ctx, Link link) {

		SendAnimation animation = new SendAnimation();
		animation.setChatId(ctx.chatId());
		animation.setAnimation(link.getHref());
	
		send(animation);
	}

	private void sendVideo(MessageContext ctx, Link link) {
		
		SendVideo video = new SendVideo();
		video.setChatId(ctx.chatId());
		video.setVideo(link.getHref());
		
		send(video);
		
	}
	
	private void send(SendPhoto message) {
		
		try {
			sender.sendPhoto(message);
		} catch (TelegramApiException e) {
			silent.send("Sorry, I can't give you any kittens right now.", Long.valueOf(message.getChatId()));
		}
		
	}
	
	private void send(SendAnimation message) {
		try {
			this.execute(message);
		} catch (TelegramApiException e) {
			silent.send("Sorry, I can't give you any kittens right now.", Long.valueOf(message.getChatId()));
		}
	}
	
	private void send(SendVideo message) {
		try {
			sender.sendVideo(message);
		} catch (TelegramApiException e) {
			silent.send("Sorry, I can't give you any kittens right now.", Long.valueOf(message.getChatId()));
		}
	}
	
}

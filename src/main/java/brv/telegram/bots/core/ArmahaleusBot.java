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
import brv.telegram.bots.services.dogs.DogService;

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
	
	@Autowired
	private DogService dogService;
	
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
	        .action(this::sendRandomCatPhoto)
	        .build();
	    
	}
	
	public Ability woof() {
	    
		CustomAbility ability = CustomAbility.WOOF;
		
		return Ability.builder()
		        .name(ability.toString()) 
		        .info(ability.getDescription())
		        .privacy(Privacy.PUBLIC)
		        .locality(Locality.ALL)
		        .input(0)
		        .action(this::sendRandomDogPhoto)
		        .build();
	}
	
	public Ability start() {
		
		CustomAbility ability = CustomAbility.START;
		
	    return Ability.builder()
	        .name(ability.toString()) 
	        .info(ability.getDescription())
	        .privacy(Privacy.PUBLIC)
	        .locality(Locality.ALL)
	        .input(0)
	        .action(ctx -> silent.send("Hi! I'm "+this.getBotUsername()+" and I will be your entertainment bot.", ctx.chatId()))
	        .build();
	    
	}
	
	public Ability help() {
		
		CustomAbility ability = CustomAbility.HELP;
		
	    return Ability.builder()
	        .name(ability.toString()) 
	        .info(ability.getDescription())
	        .privacy(Privacy.PUBLIC)
	        .locality(Locality.ALL)
	        .input(0)
	        .action(ctx -> silent.send("Everyone loves cats! Type /cat and enjoy:", ctx.chatId()))
	        .post(ctx -> this.paw().action().accept(ctx))
	        .build();
	    
	}
	
	private void sendRandomCatPhoto(MessageContext ctx) {
	
		// Query cat to service
		try {
			Optional<Media> result = catService.getRandomCat();
			sendMedia(ctx, result);
		} catch(TelegramApiException e) {
			silent.send("I cannot show you any kittens! They are hidden!", ctx.chatId());
		}
		
	}
	
	private void sendRandomDogPhoto(MessageContext ctx) {
		
		// Query do to service
		try {
			Optional<Media> result = dogService.getRandomDog();
			sendMedia(ctx, result);
		} catch(TelegramApiException e) {
			silent.send("I cannot show you any dogs! Try again!", ctx.chatId());
		}
		
	}


	private void sendMedia(MessageContext ctx, Optional<Media> mediaResult) throws TelegramApiException {
		
		// Send the media to the chat
		if(mediaResult.isPresent()) {
			
			Media media = mediaResult.get();
			switch(media.getType()) {
				case GIF: 	sendAnimation(ctx, media.getLink()); break;
				case VIDEO: sendVideo(ctx, media.getLink()); break;
				default: 	sendPhoto(ctx, media.getLink()); break;
			}
		} 

	}
	
	private void sendPhoto(MessageContext ctx, Link link) throws TelegramApiException {
		
		SendPhoto photo = new SendPhoto();
		photo.setChatId(ctx.chatId());
		photo.setPhoto(link.getHref());
		
		sender.sendPhoto(photo);

	}

	private void sendAnimation(MessageContext ctx, Link link) throws TelegramApiException {

		SendAnimation animation = new SendAnimation();
		animation.setChatId(ctx.chatId());
		animation.setAnimation(link.getHref());
	
		this.execute(animation);
	}

	private void sendVideo(MessageContext ctx, Link link) throws TelegramApiException  {
		
		SendVideo video = new SendVideo();
		video.setChatId(ctx.chatId());
		video.setVideo(link.getHref());
		
		sender.sendVideo(video);
		
	}

}

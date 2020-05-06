package brv.telegram.bots.services.common.mappers;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import brv.telegram.bots.services.common.dto.MediaType;


public class RegexMediaTypeMapper implements MediaTypeMapper {

	private static final Pattern GIF = Pattern.compile("([^\\s]+(\\.(?i)(gif))$)");
	private static final Pattern IMAGE = Pattern.compile("([^\\s]+(\\.(?i)(jpg|jpeg|png|bmp))$)");
	private static final Pattern VIDEO = Pattern.compile("([^\\s]+(\\.(?i)(mp4|webm|avi))$)");
	
	public MediaType getMediaType(String filename) {
	    
		if(StringUtils.isBlank(filename)) {
			return MediaType.OTHER;
		}
		
		if(GIF.matcher(filename).find()) {
			return MediaType.GIF;
		}
		
		if(IMAGE.matcher(filename).find()) {
			return MediaType.IMAGE;
		}
		
		if(VIDEO.matcher(filename).find()) {
			return MediaType.VIDEO;
		}
		
		return MediaType.OTHER;
	}

}

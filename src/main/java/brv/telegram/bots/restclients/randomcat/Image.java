package brv.telegram.bots.restclients.randomcat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Image {

	@JsonProperty("file")
	private String url;
	
}

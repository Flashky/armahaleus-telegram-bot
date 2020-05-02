package brv.telegram.bots.restclients.cats;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CatImage {

	@JsonProperty("file")
	private String url;
	
}

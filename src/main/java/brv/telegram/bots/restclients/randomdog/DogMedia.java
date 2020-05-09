package brv.telegram.bots.restclients.randomdog;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DogMedia {

	/**
	 * Size in bytes
	 */
	@JsonProperty("fileSizeBytes")
	private long size;
	private String url;
}

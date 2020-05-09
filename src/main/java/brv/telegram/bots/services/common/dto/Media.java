package brv.telegram.bots.services.common.dto;

import lombok.Data;

@Data
public class Media {

	private MediaType type;
	
	/**
	 * Size in bytes.
	 */
	private Long size;
	private Link link; 
}

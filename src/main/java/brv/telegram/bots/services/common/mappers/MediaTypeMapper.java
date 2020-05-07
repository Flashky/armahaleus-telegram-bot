package brv.telegram.bots.services.common.mappers;

import brv.telegram.bots.services.common.dto.MediaType;

public interface MediaTypeMapper {

	MediaType getMediaType(String filename);
}

package brv.telegram.bots.services.cats.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import brv.telegram.bots.restclients.cats.Image;
import brv.telegram.bots.services.common.dto.Media;
import brv.telegram.bots.services.common.mappers.RegexMediaTypeMapper;

@Mapper(uses = RegexMediaTypeMapper.class)
public interface MediaMapper {
	
	@Mapping(source = "url", target = "type")
	@Mapping(source = "url", target = "link.href")
	abstract Media map(Image media);
	
}

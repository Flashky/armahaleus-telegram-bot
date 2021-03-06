package brv.telegram.bots.services.cats.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import brv.telegram.bots.restclients.randomcat.Image;
import brv.telegram.bots.services.common.dto.Media;
import brv.telegram.bots.services.common.mappers.RegexMediaTypeMapper;

@Mapper(uses = RegexMediaTypeMapper.class)
public interface RandomCatMapper {
	
	@Mapping(source = "url", target = "type")
	@Mapping(source = "url", target = "link.href")
	Media map(Image media);
	
}

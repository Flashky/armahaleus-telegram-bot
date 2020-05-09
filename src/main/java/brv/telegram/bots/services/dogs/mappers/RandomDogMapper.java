package brv.telegram.bots.services.dogs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import brv.telegram.bots.restclients.randomdog.DogMedia;
import brv.telegram.bots.services.common.dto.Media;
import brv.telegram.bots.services.common.mappers.RegexMediaTypeMapper;

@Mapper(uses = RegexMediaTypeMapper.class)
public interface RandomDogMapper {
	
	@Mapping(source = "url", target = "type")
	@Mapping(source = "url", target = "link.href")
	Media map(DogMedia media);
	
}

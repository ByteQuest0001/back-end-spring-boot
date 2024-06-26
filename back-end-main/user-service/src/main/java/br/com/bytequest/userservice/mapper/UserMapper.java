package br.com.bytequest.userservice.mapper;

import br.com.bytequest.userservice.dto.CreateUserDTO;
import br.com.bytequest.userservice.dto.DetailUserDTO;
import br.com.bytequest.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	DetailUserDTO entityToDetailDTO(User user);

	User createUserDTOToEntity(CreateUserDTO user);

	@Mapping(target = "id", ignore = true)
	List<DetailUserDTO> entityToDetailDTOList(List<User> users);

}

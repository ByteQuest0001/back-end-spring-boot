package br.com.bytequest.userservice.service;

import br.com.bytequest.userservice.dto.CreateUserDTO;
import br.com.bytequest.userservice.dto.DetailUserDTO;
import br.com.bytequest.userservice.dto.UpdateUserDTO;
import br.com.bytequest.userservice.mapper.UserMapper;
import br.com.bytequest.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	@Autowired
	UserMapper mapper;

	public DetailUserDTO create(CreateUserDTO json){
		var userEntity = mapper.createUserDTOToEntity(json);
		var newUser = repository.save(userEntity);
		return mapper.entityToDetailDTO(newUser);
	}

	public List<DetailUserDTO> listAll(){
		var userEntity = repository.findAll();
		return mapper.entityToDetailDTOList(userEntity);
	}

	public DetailUserDTO listById(Long id) {
		var user = repository.getReferenceById(id);
		return mapper.entityToDetailDTO(user);
	}

	public DetailUserDTO updateUser(UpdateUserDTO json, Long id) {
		var user = repository.getReferenceById(id);
		user.update(json);
		return mapper.entityToDetailDTO(user);
	}

	public void delete(Long id){
		repository.deleteById(id);
	}
}

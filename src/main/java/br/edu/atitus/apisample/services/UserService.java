package br.edu.atitus.apisample.services;

import org.springframework.stereotype.Service;

import br.edu.atitus.apisample.entities.UserEntity;

@Service
public class UserService {

	public UserEntity save(UserEntity newUser) throws Exception {
		//TODO validar regras de negócio
		//TODO Invocar método camada repository
		
		if (newUser == null) throw new Exception("Objeto nulo");
		
		if (newUser.getName() == null || newUser.getName().isEmpty()) throw new Exception("Nome inválido");
		newUser.setName(newUser.getName().trim());
		
		if (newUser.getEmail() == null || newUser.getEmail().isEmpty()) throw new Exception("Email inválido");
		newUser.setEmail(newUser.getEmail().trim());
		
		if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) throw new Exception("Senha inválido");
		
		return newUser;
	}
	
}

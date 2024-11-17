package br.edu.atitus.apisample.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.atitus.apisample.entities.UserEntity;
import br.edu.atitus.apisample.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository repository; 
	
	
	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	public UserEntity save(UserEntity newUser) throws Exception {
		
		// Valida regras de negócio
		
		if (newUser == null) throw new Exception("Objeto nulo");
		
		if (newUser.getName() == null || newUser.getName().isEmpty()) throw new Exception("Nome inválido");
		newUser.setName(newUser.getName().trim());
		
		if (newUser.getEmail() == null || newUser.getEmail().isEmpty()) throw new Exception("Email inválido");
		if (this.repository.existsByEmail(newUser.getEmail())) throw new Exception("Já Existe um usuário com essse email");		
		newUser.setEmail(newUser.getEmail().trim());
		
		if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) throw new Exception("Senha inválida");
				
		
		// Invoca método camada repository
		this.repository.save(newUser);
		
		return newUser;
	}
	
	public List<UserEntity> findAll() throws Exception {
		var users = repository.findAll();
		return users;
	}
	
}

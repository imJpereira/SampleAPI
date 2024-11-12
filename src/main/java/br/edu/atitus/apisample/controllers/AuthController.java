package br.edu.atitus.apisample.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.apisample.dtos.SignupDTO;
import br.edu.atitus.apisample.entities.TypeUser;
import br.edu.atitus.apisample.entities.UserEntity;
import br.edu.atitus.apisample.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {	
	
	private final UserService SERVICE;
	
	public AuthController(UserService service) {
		super();
		this.SERVICE = service;
	}

	@PostMapping("/signup")
	public ResponseEntity<UserEntity> createNewUser(@RequestBody SignupDTO signup) {
		
		//TODO converter DTIOEntity
		UserEntity newUser = new UserEntity();
		BeanUtils.copyProperties(signup, newUser);
		
		newUser.setType(TypeUser.COMMON);
		
		
		//TODO invocar método camada service
		//TODO retornar entidade User
		
		return ResponseEntity.ok(newUser);
	}
}

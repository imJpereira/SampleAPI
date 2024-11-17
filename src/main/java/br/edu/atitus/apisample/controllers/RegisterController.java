package br.edu.atitus.apisample.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.apisample.dtos.RegisterDTO;
import br.edu.atitus.apisample.entities.RegisterEntity;
import br.edu.atitus.apisample.entities.UserEntity;
import br.edu.atitus.apisample.services.RegisterService;
import br.edu.atitus.apisample.services.UserService;

@RestController
@RequestMapping("/registers")
public class RegisterController {

	private final RegisterService service;
	private final UserService userService;
	
	public RegisterController(RegisterService service, UserService userService) {
		super();
		this.service = service;
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<RegisterEntity> createNewReg(@RequestBody RegisterDTO registerDTO) throws Exception {	
		
		RegisterEntity newReg = new RegisterEntity();
		BeanUtils.copyProperties(registerDTO, newReg);
		
		// Pega o primeiro usuário cadastrado, para testes
		// Quando a autenticação estiver funcionando, pegar usuário autenticado
		UserEntity user = userService.findAll().get(1);
		newReg.setUser(user);
		
		service.save(newReg);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newReg);
	}
	
	@GetMapping
	public ResponseEntity<List<RegisterEntity>> findAll() throws Exception {
		var registers = service.findAll();	
		return ResponseEntity.ok(registers);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<RegisterEntity> findById(@PathVariable UUID id) throws Exception {
		var register = service.findById(id);
		return ResponseEntity.ok(register);
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<RegisterEntity> editById(@PathVariable UUID id, @RequestBody RegisterDTO registerDTO) throws Exception {
		RegisterEntity register = service.findById(id);
		BeanUtils.copyProperties(registerDTO, register);

		var user = userService.findAll().get(0);
		register.setUser(user);
			
		service.save(register);
		
		return ResponseEntity.ok(register);
	}

	@ExceptionHandler
	public ResponseEntity<String> handlerMethod(Exception ex) {
		String msg = ex.getMessage().replaceAll("\r\n", "");	
		return ResponseEntity.badRequest().body(msg);
	}
	
}


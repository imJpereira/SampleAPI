package br.edu.atitus.apisample.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.atitus.apisample.entities.RegisterEntity;
import br.edu.atitus.apisample.repositories.RegisterRepository;

@Service
public class RegisterService {
	
	private final RegisterRepository repository;

	public RegisterService(RegisterRepository repository) {
		super();
		this.repository = repository;
	}
	
	public RegisterEntity save(RegisterEntity newReg) throws Exception {
		
		// Validações - Regras de Negócio
		if (newReg.getLatitude() > 180 || newReg.getLatitude() < -180) throw new Exception("Latitude Inválida");
		
		if (newReg.getLongitude() > 180 || newReg.getLongitude() < -180) throw new Exception("Longitude Inválida");
		
		if (newReg.getUser() == null || newReg.getUser().getId() == null ) throw new Exception("Usuário Inválido");
		
		
		//Salva no Banco
		repository.save(newReg);
				
		return newReg;
	}
	
	public List<RegisterEntity> findAll() throws Exception {
		return repository.findAll();
	}
	
	public RegisterEntity findById(UUID id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
	}
	
	public void deleteById(UUID id) throws Exception {
		//Possibilidade de fazer validações
		repository.deleteById(id);
	}
	
}

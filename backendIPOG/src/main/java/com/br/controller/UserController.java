package com.br.controller;

import com.br.exception.ResourceNotFoundException;
import com.br.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cuser/")
@RestController
public class UserController {
	
	// CRIA UMA INSTÂNCIA DO REPOSITÓRIO JPA HIBERNATE
	
	@Autowired
	private UserRepository uRep;
	
	// LISTA TODOS OS USUÁRIOS
	
	// GET - http://localhost:8080/cuser/user
	
	@GetMapping("/user")
	public List<User> listar(){
		
		return this.uRep.findAll();
		
	}
	
	// LISTA UM USUÁRIO PELO ID
	
	// GET - http://localhost:8080/cuser/user/{id}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> consultar(@PathVariable Long id){
		
		User user = this.uRep.findById(id).orElseThrow
		(() -> new ResourceNotFoundException("Usuario nao encontrado" + id));
		
		
		return ResponseEntity.ok(user);
		
	}
	
	// INSERIR UM NOVO USUÁRIO
	
	// POST - http://localhost:8080/cuser/user - dados do usuario
	
	@PostMapping("/user")
	public User inserir(@RequestBody User user) {
		
		return this.uRep.save(user);
		
	}
	
	// ALTERAR UM USUÁRIO EXISTENTE 
	
	// PUT - http://localhost:8080/cuser/user/{id}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> alterar(@PathVariable Long id, @RequestBody User user) {
		
		User usr = this.uRep.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Usuario nao encontrado: " + id));
		
		usr.setId(id);
		usr.setName(user.getName());
		usr.setEmail(user.getEmail());
		usr.setPassword(user.getPassword());
		
		User userAtt = uRep.save(user);
		
		return ResponseEntity.ok(userAtt);
		
	}
	
	// EXCLUIR UM USUÁRIO EXISTENTE 
	
	// DELETE - http://localhost:8080/cuser/user/{id}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Map<String, Boolean>> excluir (@PathVariable Long id) {
		
		User user = this.uRep.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Usuario nao encontrado: " + id));
		
		this.uRep.delete(user);
		
		Map<String, Boolean> resposta = new HashMap<>();
		resposta.put("Usuario " + id + " excluído com sucesso.", Boolean.TRUE);
		
		return ResponseEntity.ok(resposta);
		
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
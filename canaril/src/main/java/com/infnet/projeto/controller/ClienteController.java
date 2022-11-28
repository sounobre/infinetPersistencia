package com.infnet.projeto.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.projeto.dto.ClienteDTO;
import com.infnet.projeto.service.impl.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	final ClienteService clienteService;

	@GetMapping
	@ApiOperation(value = "Retorna a lista de Clientes.", notes = "Get all Clientes.")
	public ResponseEntity<Page<?>> listarTodos(@RequestParam(value = "page", defaultValue = "0") int page,
										       @RequestParam(value = "limit", defaultValue = "12") int limit,
										       @RequestParam(value = "direction", defaultValue = "asc") String direction) {	
		var sorDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "nome"));
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarTodos(pageable));		
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um Cliente pelo id.", notes = "Get Cliente.")
	public ResponseEntity<?> listarPorId(@PathVariable(value = "id") Long id) throws Exception {		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarPorId(id));		
	}
	
	@PostMapping
	@ApiOperation(value = "Cria um Cliente.", notes = "Create Cliente.")
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ClienteDTO clienteDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvar(clienteDTO));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Altera um Cliente.", notes = "Update Cliente.")
	public ResponseEntity<?> atualizar(@PathVariable(value = "id") Long id, 
											 @RequestBody @Valid ClienteDTO clienteDTO) {		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizar(id, clienteDTO));		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um Cliente.", notes = "Delete Cliente.")
	public ResponseEntity<?> excluir(@PathVariable(value = "id") Long id) throws Exception {
		clienteService.deletar(id);
		return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");		
	}
}

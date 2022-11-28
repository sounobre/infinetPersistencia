package com.infnet.projeto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.infnet.projeto.dto.CanarilDTO;
import com.infnet.projeto.service.impl.CanarilService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/canaril")
public class CanarilController {
	
	@Autowired
	private CanarilService canarilService;

	
	@GetMapping
	@ApiOperation(value = "Retorna a lista de canaril.", notes = "Get all canaril.")
	public ResponseEntity<Page<?>> listarTodos(@RequestParam(value = "page", defaultValue = "0") int page,
											   @RequestParam(value = "limit", defaultValue = "12") int limit,
											   @RequestParam(value = "direction", defaultValue = "asc") String direction) {	
		var sorDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "dataCompra"));
		
		return ResponseEntity.status(HttpStatus.OK).body(canarilService.listarTodos(pageable));
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um Canaril pelo id.", notes = "Get Canaril.")
	public ResponseEntity<?> listarPorId(@PathVariable(value = "id") Long id) throws Exception {		
		return ResponseEntity.status(HttpStatus.OK).body(canarilService.listarPorId(id));		
	}
	
	@PostMapping
	@ApiOperation(value = "Cria um Canaril.", notes = "Create Canaril.")
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CanarilDTO canarilDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(canarilService.salvar(canarilDTO));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Altera um Canaril.", notes = "Update Canaril.")
	public ResponseEntity<?> atualizar(@PathVariable(value = "id") Long id, 
									   @RequestBody @Valid CanarilDTO canarilDTO) {		
		return ResponseEntity.status(HttpStatus.OK).body(canarilService.atualizar(id, canarilDTO));		
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um Canaril.", notes = "Delete Canaril.")
	public ResponseEntity<?> excluir(@PathVariable(value = "id") Long id) throws Exception {
		canarilService.deletar(id);
		return ResponseEntity.status(HttpStatus.OK).body("Canaril deletado com sucesso!");		
	}

}

package com.infnet.projeto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.infnet.projeto.dto.ClienteDTO;

public interface IClienteService {
	
	ClienteDTO salvar(ClienteDTO clienteDTO) throws Exception;
	
	Page<ClienteDTO> listarTodos(Pageable pageable);
	
	ClienteDTO listarPorId(Long id) throws Exception;

	ClienteDTO atualizar(Long id, ClienteDTO clienteDTO);

	void deletar(Long id) throws Exception;
	
}

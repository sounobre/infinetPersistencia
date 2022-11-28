package com.infnet.projeto.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.infnet.projeto.dto.ClienteDTO;
import com.infnet.projeto.model.Cliente;
import com.infnet.projeto.repository.ClienteRepository;
import com.infnet.projeto.service.IClienteService;

@Service
public class ClienteService implements IClienteService {

	final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public ClienteDTO salvar(ClienteDTO clienteDTO) throws Exception {
		if (clienteRepository.findByCpf(clienteDTO.getCpf()).isPresent()) {
			throw new Exception("Já existe um Cliente com este CPF!");
		}
		
		return ClienteDTO.create(clienteRepository.save(Cliente.create(clienteDTO)));
	}

	@Override
	public Page<ClienteDTO> listarTodos(Pageable pageable) {
		var page = clienteRepository.findAll(pageable);
		
		return page.map(this::convertToClienteDTO);
	}
	
	private ClienteDTO convertToClienteDTO(Cliente cliente) {
		return ClienteDTO.create(cliente);
	}

	@Override
	public ClienteDTO listarPorId(Long id) throws Exception {
		var cliente = clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente com id = " + id + " não encontrado!"));
		
		return ClienteDTO.create(cliente);
	}

	@Override
	public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
		var cliente = clienteRepository.findById(id).get();
		
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setNome(clienteDTO.getNome());
		
		return ClienteDTO.create(clienteRepository.save(cliente));
	}

	@Override
	public void deletar(Long id) throws Exception {		
		var cliente = clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente com id = " + id + " não encontrado!"));
		
		try {
			clienteRepository.delete(cliente);
		} catch (Exception e) {
			throw new Exception("Cancele os canaril do Cliente antes de excluir!");
		}
		
	}

}

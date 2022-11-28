package com.infnet.projeto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.infnet.projeto.dto.CanarilCompletoDTO;
import com.infnet.projeto.dto.CanarilDTO;
import com.infnet.projeto.model.Canaril;
import com.infnet.projeto.repository.CanarilRepository;
import com.infnet.projeto.repository.ClienteRepository;
import com.infnet.projeto.service.ICanarilService;

@Service
public class CanarilService implements ICanarilService {
	
	@Autowired
	private CanarilRepository canarilRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public CanarilCompletoDTO salvar(CanarilDTO canarilDTO) throws Exception {
		var cliente = clienteRepository.findByCpf(canarilDTO.getCpf()).orElseThrow(() -> new Exception("O Cliente informado não existe!"));
		
		if (canarilRepository.findByCliente(cliente).isPresent()) {
			throw new Exception("Já exite um canaril para esse Cliente!");
		}
		
		var canaril = Canaril.create(canarilDTO);
		
		canaril.setCliente(cliente);
		
		return CanarilCompletoDTO.create(canarilRepository.save(canaril));
	}

	@Override
	public Page<CanarilCompletoDTO> listarTodos(Pageable pageable) {	
		var page = canarilRepository.findAll(pageable);
		
		return page.map(this::convertToCanarilDTO);
	}
	
	private CanarilCompletoDTO convertToCanarilDTO(Canaril canaril) {
		return CanarilCompletoDTO.create(canaril);
	}
	
	@Override
	public CanarilCompletoDTO listarPorId(Long id) throws Exception {
		var canaril = canarilRepository.findById(id).orElseThrow(() -> new Exception("Canaril com id = " + id + " não encontrado!"));
		
		return CanarilCompletoDTO.create(canaril);
	}

	@Override
	public CanarilDTO atualizar(Long id, CanarilDTO canarilDTO) {
		var canaril = canarilRepository.findById(id).get();
		
		canaril.setEndereco(canarilDTO.getEndereco());
		
		return CanarilDTO.create(canarilRepository.save(canaril));
	}

	@Override
	public void deletar(Long id) throws Exception {
		var canaril = canarilRepository.findById(id).orElseThrow(() -> new Exception("Canaril com id = " + id + " não encontrado!"));
		
		canarilRepository.delete(canaril);
	}

	
	


}
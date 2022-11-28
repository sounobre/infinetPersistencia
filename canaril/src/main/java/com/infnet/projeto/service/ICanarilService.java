package com.infnet.projeto.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.infnet.projeto.dto.CanarilCompletoDTO;
import com.infnet.projeto.dto.CanarilDTO;

public interface ICanarilService {

	CanarilCompletoDTO salvar(@Valid CanarilDTO canarilDTO) throws Exception;

	Page<CanarilCompletoDTO> listarTodos(Pageable pageable);

	CanarilCompletoDTO listarPorId(Long id) throws Exception;

	CanarilDTO atualizar(Long id, CanarilDTO canarilDTO);

	void deletar(Long id) throws Exception;

}

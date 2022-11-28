package com.infnet.projeto.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.infnet.projeto.model.Canaril;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CanarilCompletoDTO implements Serializable {

	private static final long serialVersionUID = -6251543827725455825L;

	@NotBlank
	private String endereco;
	@NotNull
	private ClienteDTO cliente;

	public static CanarilCompletoDTO create(Canaril canaril) {
		return new ModelMapper().map(canaril, CanarilCompletoDTO.class);
	}

}

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
public class CanarilDTO implements Serializable {
	
	private static final long serialVersionUID = -6950543827725455825L;
	
	@NotBlank
	private String endereco;
	@NotNull
	private String cpf;
	
	public static CanarilDTO create(Canaril canaril) {
		return new ModelMapper().map(canaril, CanarilDTO.class);
	}
	

}

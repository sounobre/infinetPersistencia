package com.infnet.projeto.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.infnet.projeto.model.Cliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -3384152602360043312L;

	@NotBlank
	private String nome;
	private String cpf;
	@NotBlank
	@Email
	private String email;

	public static ClienteDTO create(Cliente cliente) {
		return new ModelMapper().map(cliente, ClienteDTO.class);
	}

}

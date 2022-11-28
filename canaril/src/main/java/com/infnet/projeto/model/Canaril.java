package com.infnet.projeto.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.infnet.projeto.dto.CanarilCompletoDTO;
import com.infnet.projeto.dto.CanarilDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_CANARIL")
public class Canaril implements Serializable {

	private static final long serialVersionUID = -8928317227075818606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String endereco;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	public static Canaril create(CanarilDTO canarilDTO) {
		return new ModelMapper().map(canarilDTO, Canaril.class);
	}

	public static Canaril create(CanarilCompletoDTO canarilCompletoDTO) {
		return new ModelMapper().map(canarilCompletoDTO, Canaril.class);
	}

	@Override
	public String toString() {
		return "Canaril [id=" + id + ", endereco=" + endereco + ", cliente=" + cliente + "]";
	}

}

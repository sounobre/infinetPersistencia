package com.infnet.projeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infnet.projeto.model.Cliente;
import com.infnet.projeto.model.Canaril;

@Repository
public interface CanarilRepository extends JpaRepository<Canaril, Long> {
	
	Optional<Canaril> findByCliente(Cliente cliente);

}

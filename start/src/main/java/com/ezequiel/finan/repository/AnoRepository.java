package com.ezequiel.finan.repository;

import org.springframework.data.repository.CrudRepository;

import com.ezequiel.finan.model.Ano;

public interface AnoRepository extends CrudRepository<Ano, String> {
	Ano findByCodigo (long codigo);
}

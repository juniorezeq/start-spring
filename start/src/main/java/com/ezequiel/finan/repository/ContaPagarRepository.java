package com.ezequiel.finan.repository;

import org.springframework.data.repository.CrudRepository;

import com.ezequiel.finan.model.ContaPagar;
import com.ezequiel.finan.model.Mes;

public interface ContaPagarRepository extends CrudRepository<ContaPagar, String> {
	ContaPagar findById(long id);
	Iterable<ContaPagar> findByMes(Mes mes);
}

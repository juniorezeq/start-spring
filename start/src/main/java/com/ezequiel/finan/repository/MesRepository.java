package com.ezequiel.finan.repository;

import org.springframework.data.repository.CrudRepository;

import com.ezequiel.finan.model.Ano;
import com.ezequiel.finan.model.Mes;

public interface MesRepository extends CrudRepository<Mes, String> {
	Mes findById(long id);
	Mes findByDescricao(String descricao);
	Mes findByAno_Codigo(long ano_codigo);
	Iterable<Mes> findByAno(Ano ano);
}

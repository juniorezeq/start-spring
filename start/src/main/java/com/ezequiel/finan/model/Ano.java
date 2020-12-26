package com.ezequiel.finan.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ano implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	private long num_ano;
	
	@OneToMany(mappedBy="ano")
	private List<Mes> mesesAno;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public long getNum_ano() {
		return num_ano;
	}

	public void setNum_ano(long num_ano) {
		this.num_ano = num_ano;
	}

	public List<Mes> getMesesAno() {
		return mesesAno;
	}

	public void setMesesAno(List<Mes> mesesAno) {
		this.mesesAno = mesesAno;
	}
	
	
	
}

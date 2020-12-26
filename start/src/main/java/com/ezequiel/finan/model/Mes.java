package com.ezequiel.finan.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Mes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String descricao;
	private long num_mes;
    private long num_ano;
 
    
    @OneToMany(mappedBy="mes")
	private List<ContaPagar> contasMes;
    
    @ManyToOne
    private Ano ano;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getNum_mes() {
		return num_mes;
	}
	public void setNum_mes(long num_mes) {
		this.num_mes = num_mes;
	}
	public long getNum_ano() {
		return num_ano;
	}
	public void setNum_ano(long num_ano) {
		this.num_ano = num_ano;
	}
	public List<ContaPagar> getContasMes() {
		return contasMes;
	}
	public long getId() {
		return id;
	}
	public void setId(long id_mes) {
		this.id= id_mes;
	}
	public void setContasMes(List<ContaPagar> contasMes) {
		this.contasMes = contasMes;
	}
	public Ano getAno() {
		return ano;
	}
	public void setAno(Ano ano) {
		this.ano = ano;
	}
		
	
	}
	


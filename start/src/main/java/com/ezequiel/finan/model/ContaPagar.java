package com.ezequiel.finan.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.expression.ParseException;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ContaPagar implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String descricao;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate vencimento;
	private double valor;
	private int parcela;
	private boolean paga;
	
	public boolean isPaga() {
		return paga;
	}
	public void setPaga(boolean paga) {
		this.paga = paga;
	}
	@ManyToOne
	private Mes mes;

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getVencimento() {
	return vencimento;
}
	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getParcela() {
		return parcela;
	}
	public void setParcela(int parcela) {
		this.parcela = parcela;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Mes getMes() {
		return mes;
	}
	public void setMes(Mes mes) {
		this.mes = mes;
	}
	public String VencimentoFormatado() throws java.text.ParseException {
		SimpleDateFormat formatBra;   
		formatBra = new SimpleDateFormat("dd/MM/yyyy");
		try {
		      java.util.Date newData = formatBra.parse(this.vencimento.toString());
		      return (formatBra.format(newData));
		   } catch (ParseException Ex) {
		      return "Erro na conversão da data";
		   }
	}
	
public String pagamento() {
	if (paga==true) {
		return "Paga";
	}else {
		return "em aberto";
	}
}
	
}

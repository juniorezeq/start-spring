package com.ezequiel.finan.controllers;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ezequiel.finan.model.Ano;
import com.ezequiel.finan.model.ContaPagar;
import com.ezequiel.finan.model.Mes;
import com.ezequiel.finan.repository.AnoRepository;
import com.ezequiel.finan.repository.ContaPagarRepository;
import com.ezequiel.finan.repository.MesRepository;

@Controller
public class AnoController {
	
	@Autowired
	private AnoRepository anoRepository;
	@Autowired
	private MesRepository mesRepository;
	@Autowired
	private ContaPagarRepository contaRepository;
	
	@RequestMapping(value="/listaanos" , method=RequestMethod.GET)
	public ModelAndView ListaAnos(){
		ModelAndView mv = new ModelAndView("views/ano/listaAnos");
		Iterable<Ano> anos = anoRepository.findAll();
		mv.addObject("anos",  anos);
		return mv;
	}

	@RequestMapping(value="/cadastrarano", method=RequestMethod.GET)
	public String form() {
		return "views/ano/cadastrarAno";
	}
	
	@RequestMapping(value="/cadastrarano", method=RequestMethod.POST)
	public String form(Ano ano) {
		String[] meses = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
		anoRepository.save(ano);
		for (int i = 0; i<12; i++) {
			Mes mes = new Mes();
			mes.setNum_ano(ano.getNum_ano());
			mes.setNum_mes(i+1);
			mes.setDescricao(meses[i] + "-" + String.valueOf(ano.getNum_ano()));
			mes.setAno(ano);
			mesRepository.save(mes);
		}
		
		anoRepository.save(ano);
		
		return "redirect:/cadastrarano";
	}

	@RequestMapping(value="/ano{codigo}")
	public ModelAndView detalheAno(@PathVariable("codigo")long codigo) {
		ModelAndView mv = new ModelAndView("views/ano/detalheAno");
		Ano ano = anoRepository.findByCodigo(codigo);
		mv.addObject("ano", ano);
		Iterable<Mes> meses = mesRepository.findByAno(ano);
		mv.addObject("meses",meses);
		return mv;
	}
	
	@RequestMapping(value="/excluirano")
	public String excluir(long codigo) {
		Ano ano = anoRepository.findByCodigo(codigo);
		Iterable<Mes> meses = mesRepository.findByAno(ano);
		Iterator <Mes> iterator = meses.iterator();
		while(iterator.hasNext()) {
		    Mes mes = iterator.next();
			Iterable<ContaPagar> contas = contaRepository.findByMes(mes);
			Iterator <ContaPagar> contasPagar = contas.iterator();
			while(contasPagar.hasNext()) {
				ContaPagar conta = contasPagar.next();
				contaRepository.delete(conta);
			}
		    mesRepository.delete(mes);
		    }
		anoRepository.delete(ano);		
		return "redirect:/listaanos";
	}

	
}

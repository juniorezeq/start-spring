package com.ezequiel.finan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ezequiel.finan.model.ContaPagar;
import com.ezequiel.finan.model.Mes;
import com.ezequiel.finan.repository.ContaPagarRepository;
import com.ezequiel.finan.repository.MesRepository;

@Controller
public class MesController {
	
	@Autowired
	private MesRepository mesRepository;
	@Autowired
	private ContaPagarRepository contaRepository;
	
	@RequestMapping(value="/mes{descricao}", method=RequestMethod.GET)
	public ModelAndView detalheMes(@PathVariable("descricao") String descricao) {
		ModelAndView mv = new ModelAndView("views/mes/detalheMes");
		Mes mes = mesRepository.findByDescricao(descricao);
		mv.addObject("mes", mes);
		Iterable<ContaPagar> contas = contaRepository.findByMes(mes);
		mv.addObject("contas",contas);
		return mv;
	}

	@RequestMapping(value="/mes{descricao}", method = RequestMethod.POST)
	public ModelAndView detalheMesConta(@PathVariable("descricao") String descricao, ContaPagar conta) {
		ModelAndView mv = new ModelAndView("views/mes/detalheMes");
		Mes mes = mesRepository.findByDescricao(descricao);
		mv.addObject("mes", mes);
		conta.setMes(mes);
		contaRepository.save(conta);
		Iterable<ContaPagar> contas = contaRepository.findByMes(mes);
		mv.addObject("contas",contas);
		return mv;
	}
	
	@RequestMapping(value="/pagarConta")
	public ModelAndView pagarConta(long id) {
		ContaPagar conta = contaRepository.findById(id);
		conta.setPaga(true);
		contaRepository.save(conta);		
		ModelAndView mv = new ModelAndView("views/mes/detalheMes");
		Mes mes = conta.getMes();
		mv.addObject("mes", mes);
		Iterable<ContaPagar> contas = contaRepository.findByMes(mes);
		mv.addObject("contas",contas);
		return mv;
	}
	
	@RequestMapping(value="/voltarPagamento")
	public ModelAndView voltarPagamento(long id) {
		ContaPagar conta = contaRepository.findById(id);
		conta.setPaga(false);
		contaRepository.save(conta);		
		ModelAndView mv = new ModelAndView("views/mes/detalheMes");
		Mes mes = conta.getMes();
		mv.addObject("mes", mes);
		Iterable<ContaPagar> contas = contaRepository.findByMes(mes);
		mv.addObject("contas",contas);
		return mv;
	}
	
	@RequestMapping(value="/excluirConta")
	public ModelAndView excluirConta(long id) {
		ContaPagar conta = contaRepository.findById(id);
		contaRepository.delete(conta);		
		ModelAndView mv = new ModelAndView("views/mes/detalheMes");
		Mes mes = conta.getMes();
		mv.addObject("mes", mes);
		Iterable<ContaPagar> contas = contaRepository.findByMes(mes);
		mv.addObject("contas",contas);
		return mv;
	}
	
	
	
}

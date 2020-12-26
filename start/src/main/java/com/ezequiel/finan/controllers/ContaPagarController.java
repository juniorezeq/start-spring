package com.ezequiel.finan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ezequiel.finan.model.ContaPagar;
import com.ezequiel.finan.repository.ContaPagarRepository;

@Controller
public class ContaPagarController {
	
	@Autowired
	private ContaPagarRepository contaRepository;
	
	@RequestMapping(value="/listacontaspagar" , method=RequestMethod.GET)
	public ModelAndView ListaContas(){
		ModelAndView mv = new ModelAndView("views/contapagar/listacontasPagar");
		Iterable<ContaPagar> contas = contaRepository.findAll();
		mv.addObject("contas",  contas);
		return mv;
	}
	

	@RequestMapping(value="/cadastrarcontapagar", method=RequestMethod.GET)
	public String form() {
		return "views/contapagar/formContaPagar";
	}
		
	@RequestMapping(value="/cadastrarcontapagar", method=RequestMethod.POST)
	public String form(ContaPagar conta) {
		contaRepository.save(conta);		
		return "redirect:/cadastrarcontapagar";
	}

	
	@RequestMapping(value="/conta{id}")
	public ModelAndView detalheConta(@PathVariable("id")long id) {
		ModelAndView mv = new ModelAndView("views/contapagar/atualizarConta");
		ContaPagar conta = contaRepository.findById(id);
		mv.addObject("conta", conta);
		return mv;
	}
	
	@RequestMapping(value="/excluircontapagar")
	public String excluirContaPagar(long id) {
		ContaPagar conta = contaRepository.findById(id);
		contaRepository.delete(conta);		
		return "redirect:/listacontaspagar";
	}
	
	
	@PostMapping("/conta{id}")
	public String updateUser(@PathVariable("id") long id, ContaPagar conta, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        conta.setId(id);
	        return "views/contapagar/listacontaspagar";
	    }
	        
	    contaRepository.save(conta);
	    model.addAttribute("conta", contaRepository.findAll());
	    return "redirect:/listacontaspagar";
	}

}

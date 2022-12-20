package main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.Product;
import main.model.Sorder;

import main.service.SorderService;

@Controller
public class SorderController {

	@Autowired
	private SorderService sorderService;
	
	
	@GetMapping("/addSorder")
	public String showSorderForm(Model model) {
		model.addAttribute("sorder", new Sorder());
		return "addSorder";
	}
	
	@PostMapping("/submitSorder")
	public String showSorderData(@Valid @ModelAttribute Sorder sorder, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addSorder";
		}
		sorderService.saveOrUpdate(sorder);
		return "redirect:showSorders";
	}
	
	@GetMapping("/showSorders")
	public String getSorders(Model model) {
		List<Sorder> sorders=sorderService.getAll();
		model.addAttribute("sorders", sorders);
		return "sorders";
	}
	
	@GetMapping("/deleteSorder/{id}")
	public String deleteSorder(@PathVariable int id) {
		Sorder sorder = sorderService.getById(id);
		if(sorder != null) {
			sorderService.delete(id);
		}
		return "redirect:../showSorders";
	}
	
	@GetMapping("/editSorder/{id}")
	public String editSorder(@PathVariable int id, Model model) {
		Sorder sorder = sorderService.getById(id);
		if(sorder != null) {
			model.addAttribute("sorder", sorder);
			return "addSorder";
		}
		return "redirect:../showSorders";
	}
	
}

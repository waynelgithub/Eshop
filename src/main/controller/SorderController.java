package main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.dao.SorderDAO;
import main.model.Porder;
import main.model.Sorder;
import main.model.Tour;
import main.service.SorderService;

@Controller
public class SorderController {

	@Autowired
	private SorderService sorderService;
	
	
	@GetMapping("/addSorder")
	public String showForm(Model model) {
		model.addAttribute("sorder", new Sorder());
		return "addSorder";
	}
	
	@PostMapping("/submitSorder")
	public String showTourData(@Valid @ModelAttribute Sorder sorder, BindingResult bindingResult) {
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
	
}

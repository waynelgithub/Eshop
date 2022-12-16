package main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.model.Porder;
import main.model.Tour;

@Controller
public class PorderController {

	private List<Porder> porders = new ArrayList<>();
	
//	@RequestMapping("/")
//	public String getHome() {
//		return "home";
//	}
	
	@GetMapping("/addPorder")
	public String showForm(Model model) {
		model.addAttribute("porder", new Porder());
		return "addPorder";
	}
	
	@PostMapping("/submitPorder")
	public String showTourData(@Valid @ModelAttribute Porder porder, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addPorder";
		}
		porders.add(porder);
		return "redirect:showPorders";
	}
	
	@GetMapping("/showPorders")
	public String getTours(Model model) {
		model.addAttribute("porders", porders);
		return "porderList";
	}
	
}

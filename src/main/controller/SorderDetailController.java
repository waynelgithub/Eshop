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
import main.model.SorderDetail;
import main.model.Tour;
import main.service.ProductService;
import main.service.SorderDetailService;
import main.service.SorderService;

@Controller
public class SorderDetailController {

	@Autowired
	private SorderDetailService sorderDetailService;
	
	@Autowired
	private SorderService sorderService;
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/addSorderDetail")
	public String showSorderDetailForm(Model model) {
		List<Sorder> sorders = sorderService.getAll();
		model.addAttribute("sorders", sorders);
		List<Product> products = productService.getAll();
		model.addAttribute("products", products);		
		
		model.addAttribute("sorderDetail", new SorderDetail());
		return "addSorderDetail";
	}
	
	@PostMapping("/submitSorderDetail")
	public String showSorderDetailData(@Valid @ModelAttribute SorderDetail sorderDetail, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addSorderDetail";
		}
		sorderDetailService.saveOrUpdate(sorderDetail);
		return "redirect:showSorderDetail";
	}
	
	@GetMapping("/showSorderDetail")
	public String getSorderDetails(Model model) {
		List<SorderDetail> sorderDetails=sorderDetailService.getAll();
		model.addAttribute("sorderDetails", sorderDetails);
		return "sorderDetails";
	}
	
	@GetMapping("/deleteSorderDetail/{id}")
	public String deleteSorderDetail(@PathVariable int id) {
		SorderDetail sorderDetail = sorderDetailService.getById(id);
		if(sorderDetail != null) {
			sorderDetailService.delete(id);
		}
		return "redirect:../showSorderDetail";
	}
	
	@GetMapping("/editSorderDetail/{id}")
	public String editSorderDetail(@PathVariable int id, Model model) {
		SorderDetail sorderDetail = sorderDetailService.getById(id);
		if(sorderDetail != null) {
			model.addAttribute("sorderDetail", sorderDetail);
			return "addSorderDetail";
		}
		return "redirect:../showSorderDetail";
	}
	
}

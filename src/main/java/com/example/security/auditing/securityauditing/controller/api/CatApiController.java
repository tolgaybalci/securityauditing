package com.example.security.auditing.securityauditing.controller.api;

import com.example.security.auditing.securityauditing.domain.Cat;
import com.example.security.auditing.securityauditing.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created on February, 2018
 *
 * @author cylon
 */
@RestController
@RequestMapping("/api/cats")
public class CatApiController {

	@Autowired
	private CatRepository catRepository;

	@GetMapping("")
	public Iterable<Cat> getCatsList(){
		return catRepository.findAll();
	}

	@GetMapping("/new")
	public String getNewCat(Model model){
		model.addAttribute("cat", new Cat());
		return "cat/createCat";
	}

	@PostMapping("/new")
	public String postNewCat(@ModelAttribute @Valid Cat cat, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "cat/createCat";
		}
		catRepository.save(cat);
		return "redirect:/cats";
	}

	@GetMapping("/{id}")
	public String showCatList(@PathVariable Long id, Model model){
		model.addAttribute("cat", catRepository.findById(id).get());
		return "cat/showCat";
	}

	@GetMapping("/{id}/update")
	public String getUpdateCat(@PathVariable Long id, Model model){
		model.addAttribute("cat", catRepository.findById(id).get());
		return "cat/updateCat";
	}

	@PostMapping("/{id}/update")
	public String postUpdateCat(@ModelAttribute Cat cat){
		catRepository.save(cat);
		return "redirect:/cats";
	}

	@PostMapping("/{id}/delete")
	public String postCatDelete(@PathVariable Long id){

		Cat cat = catRepository.findById(id).get();
		catRepository.delete(cat);
		return "redirect:/cats";
	}
}
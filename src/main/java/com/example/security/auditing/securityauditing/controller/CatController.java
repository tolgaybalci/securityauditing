package com.example.security.auditing.securityauditing.controller;

import com.example.security.auditing.securityauditing.domain.Cat;
import com.example.security.auditing.securityauditing.domain.validation.validator.CatValidator;
import com.example.security.auditing.securityauditing.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new CatValidator());
    }

    @GetMapping("")
    public String getCatsList(Model model){
        model.addAttribute("cats", catRepository.findAll());
        return "cat/catList";
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
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

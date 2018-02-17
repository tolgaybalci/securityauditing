package com.example.security.auditing.securityauditing.controller;

import com.example.security.auditing.securityauditing.domain.Cat;
import com.example.security.auditing.securityauditing.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @GetMapping("")
    public String getCatsList(Model model){
        model.addAttribute("cats", catRepository.findAll());
        return "cats/catList";
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/new")
    public String getNewCat(Model model){
        model.addAttribute("cat", new Cat());
        return "cats/createCat";
    }

    @PostMapping("/new")
    public String postNewCat(@ModelAttribute @Valid Cat cat, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "cats/createCat";
        }
        catRepository.save(cat);
        return "redirect:/cats";
    }

    @GetMapping("/{id}")
    public String showCatList(@PathVariable Long id, Model model){
        model.addAttribute("cat", catRepository.findById(id).get());
        return "cats/showCat";
    }

    @GetMapping("/{id}/update")
    public String getUpdateCat(@PathVariable Long id, Model model){
        model.addAttribute("cat", catRepository.findById(id).get());
        return "cats/updateCat";
    }

    @PostMapping("/{id}/update")
    public String postUpdateCat(@ModelAttribute Cat cat){
        catRepository.save(cat);
        return "redirect:/cat";
    }

    @PostMapping("{/id}/delete")
    public String postCatDelete(@PathVariable Long id){

        Cat cat = catRepository.findById(id).get();
        catRepository.delete(cat);
        return "redirect:/cats";
    }
}

package ru.gatsbyx.simplecrudapp.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.gatsbyx.simplecrudapp.dao.PersonDAO;
import ru.gatsbyx.simplecrudapp.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
	
	private PersonDAO personDAO;
	
	
	public PeopleController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("people", personDAO.index());
		return "people/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDAO.get(id));
		return "people/show";	
	}
	
	@GetMapping("/new")
	public String newPerson(@ModelAttribute("person") Person person) {
		return "people/new";
	}
	
	@PostMapping()
	public String save( @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("Has errors");
			return "people/new";
		}
		
		personDAO.save(person);
		return "redirect:/people";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDAO.get(id));
		return "people/edit";
	}
	
	@PatchMapping("/{id}")
	public String patch(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
		if(bindingResult.hasErrors()) {
			System.out.println("Has errors");
			return "people/edit";
		}
		
		personDAO.update(id, person);
		return "redirect:/people";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		personDAO.delete(id);
		return "redirect:/people";
	}
	
	
	
	
	
	

}

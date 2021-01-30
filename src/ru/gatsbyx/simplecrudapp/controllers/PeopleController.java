package ru.gatsbyx.simplecrudapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.gatsbyx.simplecrudapp.dao.PersonDAO;

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
		model.addAttribute("person", personDAO.show(id));
		return "people/show";	
	}
	
	

}

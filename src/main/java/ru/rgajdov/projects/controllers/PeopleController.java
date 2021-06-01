package ru.rgajdov.projects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rgajdov.projects.dao.PersonDAO;
import ru.rgajdov.projects.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        // Получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // Получим одного человека по id из DAO и передадим на отображение в представление
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    /*
    @GetMapping("/new")
    public String newPerson(Model model) {
        // Отображаем форму для добавления нового people
        model.addAttribute("person", new Person());
        return "people/new";
    }
    */
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        // Отображаем форму для добавления нового people
        //model.addAttribute("person", personDAO.show(id));
        personDAO.save(person);
        return "redirect:/people";
    }
}

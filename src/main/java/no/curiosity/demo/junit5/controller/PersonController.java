package no.curiosity.demo.junit5.controller;

import no.curiosity.demo.junit5.domain.Person;
import no.curiosity.demo.junit5.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "person/")
    public List<Person> people() {
        return personService.getPeople();
    }

    @GetMapping(value = "hello/")
    public String hello() {
        return "Hello";
    }
}

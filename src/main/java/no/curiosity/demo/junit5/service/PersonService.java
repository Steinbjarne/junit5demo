package no.curiosity.demo.junit5.service;

import no.curiosity.demo.junit5.domain.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static no.curiosity.demo.junit5.domain.Gender.female;
import static no.curiosity.demo.junit5.domain.Gender.male;

@Service
public class PersonService {
    public List<Person> getPeople() {
        List<Person> people = new ArrayList<Person>();
        people.addAll(asList(
                Person.builder().firstName("Tom").lastName("Liten").age(38).gender(male).build(),
                Person.builder().firstName("Eva").lastName("Liten").age(32).gender(female).build(),
                Person.builder().firstName("Siv").lastName("Liten").age(3).gender(female).build(),
                Person.builder().firstName("Tore").lastName("Tang").age(62).gender(male).build(),
                Person.builder().firstName("Snurre").lastName("Sprett").age(18).gender(female).build(),
                Person.builder().firstName("Donald").lastName("DUck").age(22).gender(female).build(),
                Person.builder().firstName("Dolly").lastName("Duck").age(26).gender(female).build()
        ));
        return people;
    }

    public boolean getException() {
        throw new RuntimeException();
    }
}

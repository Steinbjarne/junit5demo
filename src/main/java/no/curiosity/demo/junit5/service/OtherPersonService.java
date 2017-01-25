package no.curiosity.demo.junit5.service;

import no.curiosity.demo.junit5.domain.Person;
import org.springframework.stereotype.Service;

import static no.curiosity.demo.junit5.domain.Gender.male;

@Service
public class OtherPersonService {
    public Person getSinglePerson() {
        return Person.builder().firstName("Tom").lastName("Liten").age(38).gender(male).build();
    }
}

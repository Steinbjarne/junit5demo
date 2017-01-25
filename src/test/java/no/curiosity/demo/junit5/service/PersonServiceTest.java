package no.curiosity.demo.junit5.service;

import no.curiosity.demo.junit5.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("When person list is retrieved")
class PersonServiceTest {
    private PersonService service;

    @BeforeEach
    public void setUp() {
        service = new PersonService();
    }

    @Test
    @DisplayName("It should contain seven people")
    public void shouldContainSevenPeopleWhenPersonListIsRetrieved() {
        List<Person> people = service.getPeople();

        assertEquals(7, people.size());
    }

    @Test
    @DisplayName("It should contain a small family with the name little")
    public void shouldFindThreeLittles() {
        final List<Person> people = service.getPeople();
        long l = people.stream().filter(p -> p.lastName().equals("Liten")).count();

        assertEquals(l, 3);
    }

    @Test
    @DisplayName("It should get exception when calling getException")
    public void shouldGetException() {
        assertThrows(RuntimeException.class, () -> service.getException());
    }
}
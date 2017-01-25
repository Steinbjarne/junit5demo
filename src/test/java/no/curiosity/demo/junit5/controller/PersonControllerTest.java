package no.curiosity.demo.junit5.controller;

import no.curiosity.demo.junit5.Application;
import no.curiosity.demo.junit5.context.SpringExtension;
import no.curiosity.demo.junit5.domain.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static no.curiosity.demo.junit5.domain.Gender.female;
import static no.curiosity.demo.junit5.domain.Gender.male;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
@DisplayName("Testing runner's supliment")
class PersonControllerTest {
    @Autowired
    private PersonController controller;

    @Nested
    @DisplayName("Testing hello in controller")
    public class HelloTest {
        @Test
        @DisplayName("Application context running")
        public void shouldHaveProperApplicationStartup() {
            String hello = controller.hello();

            assertTrue(hello.equals("Hello"));
        }
    }

    @Nested
    @DisplayName("Testing person in controller")
    public class PersonTest {
        @Test
        @DisplayName("It should have seven items")
        public void shouldHaveSevenPeople() {
            List<Person> people = controller.people();

            assertEquals(people.size(), 7);
        }

        @Test
        @DisplayName("Arrays should contain same elements")
        public void shouldHaveSameContent() {
            List<Person> expected = createPersonList();
            List<Person> actual = controller.people();

            assertAll(
                    () ->assertEquals(expected.stream().map(Person::firstName).collect(Collectors.toSet()),
                            actual.stream().map(Person::firstName).collect(Collectors.toSet())),
                    () ->assertEquals(expected.stream().map(Person::lastName).collect(Collectors.toSet()),
                            actual.stream().map(Person::lastName).collect(Collectors.toSet())),
                    () ->assertEquals(expected.stream().map(Person::age).collect(Collectors.toSet()),
                            actual.stream().map(Person::age).collect(Collectors.toSet())),
                    () ->assertEquals(expected.stream().map(Person::gender).collect(Collectors.toSet()),
                            actual.stream().map(Person::gender).collect(Collectors.toSet()))
                    );
        }

        public List<Person> createPersonList() {
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
    }
}
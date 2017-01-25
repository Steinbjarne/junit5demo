package no.curiosity.demo.junit5.service;

import no.curiosity.demo.junit5.domain.Gender;
import no.curiosity.demo.junit5.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("In person service")
class PersonServiceNestedTest {
    private PersonService service;

    @BeforeEach
    public void setUp() {
        service = new PersonService();
    }

    @Nested
    @DisplayName("When using getPeople")
    public class TestingGetPerson {
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
    }

    @Nested
    @DisplayName("When using getException")
    public class TestingException {
        @Test
        @DisplayName("It should get exception when calling getException")
        public void shouldGetException() {
            assertThrows(RuntimeException.class, () -> service.getException());
        }
    }

    @Nested
    @DisplayName("When demonstrating assertAll")
    public class TestAssertAll {
        private OtherPersonService otherService;
        @BeforeEach
        public void setUp() {
            otherService = new OtherPersonService();
        }

        @Test
        @DisplayName("All test results should be displayed")
        public void shouldDisplayAllTestsEvenWhenOneFails() {
            Person singlePerson = otherService.getSinglePerson();

            //assumeTrue(singlePerson.gender() == Gender.male);

            assertEquals(singlePerson.firstName(), "Tom");
//            assertEquals(singlePerson.gender(), Gender.female);
            assertEquals(singlePerson.lastName(), "Liten");
            assertEquals(singlePerson.age(), 38);
        }
    }
}
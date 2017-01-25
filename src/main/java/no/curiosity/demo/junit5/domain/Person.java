package no.curiosity.demo.junit5.domain;

import java.io.Serializable;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;

    private Person(String firstName, String lastName, int age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public int age() {
        return age;
    }

    public Gender gender() {
        return gender;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private int age;
        private Gender gender;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            return new Person(firstName, lastName, age, gender);
        }
    }
}

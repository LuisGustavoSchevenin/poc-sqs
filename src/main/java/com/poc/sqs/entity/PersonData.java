package com.poc.sqs.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PersonData {

    private String name;
    private int age;
    private Genre genre;

    public Person toDomain() {
        return Person.builder()
                .name(this.name)
                .age(this.age)
                .genre(this.genre)
                .build();
    }
}

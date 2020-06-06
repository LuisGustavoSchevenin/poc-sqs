package com.poc.sqs.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Person {

    private String name;
    private int age;
    private Genre genre;
}

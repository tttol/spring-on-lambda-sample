package io.github.tttol.springonlambdasample.ui.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

@Value
@JsonPropertyOrder({"userId", "name", "age"})
public class PersonDto {
    String userId;
    String name;
    int age;
}

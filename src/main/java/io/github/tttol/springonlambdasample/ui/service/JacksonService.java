package io.github.tttol.springonlambdasample.ui.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import io.github.tttol.springonlambdasample.ui.dto.PersonDto;
import org.springframework.stereotype.Service;

@Service
public class JacksonService {
    public String getCsv(PersonDto personDto) throws JsonProcessingException {
        var csvMapper = new CsvMapper();
        var schema = csvMapper.schemaFor(PersonDto.class).withoutHeader();

        return csvMapper.writer(schema).writeValueAsString(personDto);
    }
}

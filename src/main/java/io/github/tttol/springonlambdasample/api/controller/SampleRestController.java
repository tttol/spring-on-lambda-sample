package io.github.tttol.springonlambdasample.api.controller;

import io.github.tttol.springonlambdasample.api.dto.ResponseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sample")
public class SampleRestController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto sample() {
        return new ResponseDto("OK", LocalDateTime.now());
    }
}

package io.github.tttol.springonlambdasample.api.dto;

import java.time.LocalDateTime;

public record ResponseDto(String result, LocalDateTime executionDateTime) {
}

package io.github.tttol.springonlambdasample.ui.form;

import jakarta.validation.constraints.*;

public record SampleForm(
        @Pattern(regexp = "^[a-zA-Z0-9_-]{1,20}$") @NotBlank String userId,
        @NotBlank String name
) { }

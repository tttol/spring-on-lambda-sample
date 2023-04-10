package io.github.tttol.springonlambdasample.ui.form;

import javax.validation.constraints.*;

public record SampleForm(
        @Pattern(regexp = "^[a-zA-Z0-9_-]{1,20}$", message = "{validation.userId.pattern}") @NotBlank String userId,
        @NotBlank String name
) { }

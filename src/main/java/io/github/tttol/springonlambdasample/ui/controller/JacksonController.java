package io.github.tttol.springonlambdasample.ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.tttol.springonlambdasample.ui.dto.PersonDto;
import io.github.tttol.springonlambdasample.ui.form.SampleForm;
import io.github.tttol.springonlambdasample.ui.service.JacksonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/ui/jackson")
@Slf4j
@RequiredArgsConstructor
public class JacksonController {
    private final JacksonService jacksonService;

    @GetMapping
    public String index() {
        return "jackson";
    }

    @GetMapping("/csv/show")
    public String show(Model model) {
        try {
            model.addAttribute("csvString",
                    jacksonService.getCsv(new PersonDto("A001", "Thomas, aka Tom", 20)));
        } catch (JsonProcessingException e) {
            log.warn("Failed to show CSV.", e);
        }
        return "jackson";
    }

    @GetMapping("/csv/download")
    public String download() {
        return "jackson";
    }
}

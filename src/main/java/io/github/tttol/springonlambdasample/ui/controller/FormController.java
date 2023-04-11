package io.github.tttol.springonlambdasample.ui.controller;

import io.github.tttol.springonlambdasample.ui.form.SampleForm;
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
@RequestMapping("/ui/form")
@Slf4j
public class FormController {
    @GetMapping
    public String index(Model model,
                         @ModelAttribute("sampleForm") SampleForm sampleForm,
                         @ModelAttribute("submitResult") String submitResult) {
        var form = Optional.ofNullable(sampleForm)
                .orElse(new SampleForm(null, null));
        model.addAttribute("sampleForm", form);
        model.addAttribute("submitResult", submitResult);
        return "form";
    }

    @PostMapping("submit")
    public String send(@ModelAttribute("sampleForm") @Validated SampleForm sampleForm,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            log.debug("validation error. detail={}", result.getFieldErrors());
            model.addAttribute("sampleForm", sampleForm);
            return "form";
        }

        redirectAttributes.addFlashAttribute("sampleForm", sampleForm);
        redirectAttributes.addFlashAttribute("submitResult", sampleForm.toString());
        return "redirect:/ui/form";
    }
}

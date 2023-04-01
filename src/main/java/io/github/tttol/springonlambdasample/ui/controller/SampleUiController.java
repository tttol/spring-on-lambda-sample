package io.github.tttol.springonlambdasample.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ui/sample")
public class SampleUiController {
    @GetMapping
    public String top(Model model) {
        return "top";
    }

    @PostMapping("send")
    public String send(RedirectAttributes redirectAttributes) {
        return "redirect:/top";
    }
}

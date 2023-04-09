package com.warehouse.work.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.warehouse.work.entity.User;
import com.warehouse.work.service.UserServiceImpl;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistratoinController {

    private final UserServiceImpl userService;

    @PostMapping
    public String registraionUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        boolean success = userService.userRegistration(user);
        String response = success ? "Успешно зарегистрирован" : "Ошибка регистрации. Попробуйте позже.";
        redirectAttributes.addFlashAttribute("success", response);
        if (success)
            return "redirect:/";
        return "redirect:/registration";
    }

    @GetMapping
    String getRegistration() {
        return "registration";
    }
}

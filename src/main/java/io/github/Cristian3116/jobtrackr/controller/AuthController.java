package io.github.Cristian3116.jobtrackr.controller;

import io.github.Cristian3116.jobtrackr.model.User;
import io.github.Cristian3116.jobtrackr.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result, // Trebuie să fie FIX aici
                               Model model) {

        // Dacă ai erori de @Size sau @NotBlank, oprește-te aici
        if (result.hasErrors()) {
            return "register";
        }

        // Verifică duplicatul
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameError", "Username is already taken.");
            // Nu uita să returnezi "register", nu redirect!
            return "register";
        }

        userService.registerUser(user.getUsername(), user.getPassword(), "USER");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}

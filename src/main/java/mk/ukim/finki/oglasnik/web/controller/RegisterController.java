package mk.ukim.finki.oglasnik.web.controller;

import mk.ukim.finki.oglasnik.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.oglasnik.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.oglasnik.service.AuthService;
import mk.ukim.finki.oglasnik.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
   private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

   @PostMapping
    public String registerUser(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String lastname,
                           @RequestParam String contactNumber,
                           @RequestParam String contactEmail
                           ) {
        try{
            this.userService.create(username, password, repeatPassword, name, lastname, contactEmail,contactNumber);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }

    }
}

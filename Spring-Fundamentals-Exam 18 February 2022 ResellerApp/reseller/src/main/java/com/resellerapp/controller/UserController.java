package com.resellerapp.controller;

import com.resellerapp.model.dto.LoginUserDTO;
import com.resellerapp.model.dto.RegisterUserDTO;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{
    private final LoggedUser loggedUser;
    private final UserService userService;

    @Autowired
    public UserController(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @ModelAttribute(name = "registerUserDTO")
    public RegisterUserDTO registerUserDTO(){
        return new RegisterUserDTO();
    }

    @ModelAttribute(name = "loginUserDTO")
    public LoginUserDTO loginUserDTO(){
        return new LoginUserDTO();
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        if (this.loggedUser.isLogged()) {
            return super.view("home");
        }

        return super.view("login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@Valid LoginUserDTO loginUserDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginUserDTO",
                    bindingResult);
            return super.redirect("login");
        }

        if (!this.userService.login(loginUserDTO)){
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute("invalidCreds", true);
            return super.redirect("login");
        }

        return super.redirect("/home");
    }

    @GetMapping("/register")
    public ModelAndView getRegister() {
        if (this.loggedUser.isLogged()) {
            return super.view("home");
        }

        return super.view("register");
    }

    @PostMapping("/register")
    public ModelAndView postRegister(@Valid RegisterUserDTO registerUserDTO,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())){
            bindingResult.addError(new FieldError(
                    "differentConfirmPassword",
                    "confirmPassword",
                    "Passwords must be the same."));
        }

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerUserDTO",
                    bindingResult);
            return super.redirect("register");
        }

        this.userService.register(registerUserDTO);

        return super.redirect("login");
    }

    @GetMapping("/logout")
    public ModelAndView getLogout(){
        if (!this.loggedUser.isLogged()){
            return super.redirect("login");
        }

        this.userService.logOut();
        return super.redirect("/");
    }
}

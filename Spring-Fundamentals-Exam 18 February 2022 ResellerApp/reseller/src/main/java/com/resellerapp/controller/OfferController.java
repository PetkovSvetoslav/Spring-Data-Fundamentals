package com.resellerapp.controller;

import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.service.ConditionService;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController extends BaseController{
    private final LoggedUser loggedUser;
    private final ConditionService conditionService;
    private final OfferService offerService;

    @Autowired
    public OfferController(LoggedUser loggedUser, ConditionService conditionService, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.conditionService = conditionService;
        this.offerService = offerService;
    }

    @ModelAttribute(name = "addOfferDTO")
    public AddOfferDTO addOfferDTO(){
        return new AddOfferDTO();
    }

    @GetMapping("/add")
    public ModelAndView getAddOffer(ModelAndView modelAndView){
        if (!loggedUser.isLogged()){
            return super.redirect("/users/login");
        }

        List<String> conditionNames = this.conditionService.findAllConditionNames();

        modelAndView.addObject("conditionNames", conditionNames);

        return super.view("offer-add", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView postAddOffer(@Valid AddOfferDTO addOfferDTO,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);

            return super.redirect("add");
        }

        this.offerService.addOffer(addOfferDTO);

        return super.redirect("/home");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteOffer(@PathVariable UUID id){
        this.offerService.deleteOffer(id);

        return super.redirect("/home");
    }

    @GetMapping("/buy/{id}")
    public ModelAndView buyOffer(@PathVariable UUID id){
        this.offerService.buyOffer(id);

        return super.redirect("/home");
    }
}

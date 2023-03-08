package com.resellerapp.controller;

import com.resellerapp.model.dto.LoggedUserOfferInfoDTO;
import com.resellerapp.model.dto.OtherOffersInfoDTO;
import com.resellerapp.model.dto.UserBoughtOffersDTO;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController extends BaseController{
    private final LoggedUser loggedUser;
    private final OfferService offerService;

    @Autowired
    public HomeController(LoggedUser loggedUser, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.offerService = offerService;
    }

    @GetMapping("/")
    public ModelAndView getGuestHome(){
        if (this.loggedUser.isLogged()){
            return super.redirect("home");
        }

        return super.view("index");
    }

    @GetMapping("/home")
    public ModelAndView getLoggedHome(ModelAndView modelAndView){
        if (!this.loggedUser.isLogged()){
            return super.redirect("/");
        }

        List<LoggedUserOfferInfoDTO> myOffers = this.offerService.findMyOffers();
        modelAndView.addObject("myOffers", myOffers);

        List<OtherOffersInfoDTO> otherOffers = this.offerService.findAllOtherAvailableOffers();
        modelAndView.addObject("otherOffers", otherOffers);

        int otherOffersCount = otherOffers.size();
        modelAndView.addObject("otherOffersCount", otherOffersCount);

        List<UserBoughtOffersDTO> boughtOffers = this.offerService.findBoughtOffersByUser();
        modelAndView.addObject("boughtOffers", boughtOffers);

        return super.view("home", modelAndView);
    }
}

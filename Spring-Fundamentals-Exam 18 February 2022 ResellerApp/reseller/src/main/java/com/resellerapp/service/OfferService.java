package com.resellerapp.service;

import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.dto.LoggedUserOfferInfoDTO;
import com.resellerapp.model.dto.OtherOffersInfoDTO;
import com.resellerapp.model.dto.UserBoughtOffersDTO;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ConditionService conditionService;
    private final UserService userService;
    private final LoggedUser loggedUser;

    @Autowired
    public OfferService(OfferRepository offerRepository, ModelMapper modelMapper, ConditionService conditionService, UserService userService, LoggedUser loggedUser) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.conditionService = conditionService;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        Offer offer = this.modelMapper.map(addOfferDTO, Offer.class);

        ConditionName name = ConditionName.valueOf(addOfferDTO.getConditionStr().toUpperCase());
        Condition condition = this.conditionService.findByConditionName(name);
        offer.setCondition(condition);

        User user = getCurrentUser();
        offer.setUser(user);

        this.offerRepository.save(offer);
    }

    public List<LoggedUserOfferInfoDTO> findMyOffers() {
        User user = getCurrentUser();

        return this.offerRepository.findAllOffersByUserId(user.getId());
    }

    public List<OtherOffersInfoDTO> findAllOtherAvailableOffers() {
        User user = getCurrentUser();

        return this.offerRepository.findAllOtherAvailableOffers(user.getId());
    }


    public void deleteOffer(UUID id) {
        Optional<Offer> offer = this.offerRepository.findById(id);

        this.offerRepository.delete(offer.get());
    }

    public void buyOffer(UUID id) {
        User user = getCurrentUser();
        Optional<Offer> offer = this.offerRepository.findById(id);
        offer.get().setBuyer(user);
        offer.get().setUser(null);
        this.offerRepository.save(offer.get());
    }

    public List<UserBoughtOffersDTO> findBoughtOffersByUser() {
        User user = getCurrentUser();

        return this.offerRepository.findAllBoughtOffersByUser(user.getId());
    }

    private User getCurrentUser() {
        return this.userService.findByUsername(this.loggedUser.getUsername());
    }
}

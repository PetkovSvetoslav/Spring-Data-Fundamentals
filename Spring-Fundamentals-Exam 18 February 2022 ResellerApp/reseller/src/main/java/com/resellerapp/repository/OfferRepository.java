package com.resellerapp.repository;

import com.resellerapp.model.dto.LoggedUserOfferInfoDTO;
import com.resellerapp.model.dto.OtherOffersInfoDTO;
import com.resellerapp.model.dto.UserBoughtOffersDTO;
import com.resellerapp.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    @Query("SELECT new com.resellerapp.model.dto.LoggedUserOfferInfoDTO(o.id, o.description, o.price, o.condition.conditionName) " +
            "FROM Offer o " +
            "WHERE o.user.id = :id")
    List<LoggedUserOfferInfoDTO> findAllOffersByUserId(UUID id);

    @Query("SELECT new com.resellerapp.model.dto.OtherOffersInfoDTO(o.id, o.description, o.price, o.condition.conditionName, o.user.username) " +
            "FROM Offer o " +
            "WHERE o.user.id <> :id AND o.buyer.id = null")
    List<OtherOffersInfoDTO> findAllOtherAvailableOffers(UUID id);

    @Query("SELECT new com.resellerapp.model.dto.UserBoughtOffersDTO(o.description, o.price) " +
            "FROM Offer o " +
            "WHERE o.buyer.id = :id")
    List<UserBoughtOffersDTO> findAllBoughtOffersByUser(UUID id);
}

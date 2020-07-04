package com.westcoastdealershop.services;

import com.westcoastdealershop.models.bindingModels.OfferAddBindingModel;
import com.westcoastdealershop.models.bindingModels.OfferViewBindingModel;
import com.westcoastdealershop.models.entities.Offer;
import com.westcoastdealershop.models.serviceModels.OfferServiceModel;

import java.util.ArrayList;
import java.util.List;

public interface OfferService {
    ArrayList<OfferServiceModel> getAllOffers();
    Offer getOfferById(String id);
    OfferServiceModel createOffer(OfferAddBindingModel offerAddBindingModel);
    Offer updateOffer(Offer offer);
    Offer deleteOffer(String id);
    long offersCount();
}

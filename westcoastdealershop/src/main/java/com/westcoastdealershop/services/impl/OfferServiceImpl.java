package com.westcoastdealershop.services.impl;

import com.westcoastdealershop.models.bindingModels.OfferAddBindingModel;
import com.westcoastdealershop.models.bindingModels.OfferViewBindingModel;
import com.westcoastdealershop.models.entities.enums.VehCondition;
import com.westcoastdealershop.models.entities.enums.EngineType;
import com.westcoastdealershop.models.entities.Offer;
import com.westcoastdealershop.models.entities.enums.TransmissionType;
import com.westcoastdealershop.models.serviceModels.ModelServiceModel;
import com.westcoastdealershop.models.serviceModels.OfferServiceModel;
import com.westcoastdealershop.repositories.OfferRepository;
import com.westcoastdealershop.services.ModelService;
import com.westcoastdealershop.services.OfferService;
import com.westcoastdealershop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelService modelService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelService modelService, UserService userService,
                            ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelService = modelService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArrayList<OfferServiceModel> getAllOffers() {
        return offerRepository.findAll()
                .stream().map(o -> this.modelMapper.map(o, OfferServiceModel.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Offer getOfferById(String id) {
        return this.offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Offer with ID: %s doesn't exist!", id)));
    }

    @Override
    public OfferServiceModel createOffer(OfferAddBindingModel offerAddBindingModel) {

        OfferServiceModel offerServiceModel = this.modelMapper.map(offerAddBindingModel, OfferServiceModel.class);

        offerServiceModel.setVehCondition(VehCondition.valueOf(offerAddBindingModel.getCondition()));
        offerServiceModel.setEngine(EngineType.valueOf(offerAddBindingModel.getEngine()));
        offerServiceModel.setTransmission(TransmissionType.valueOf(offerAddBindingModel.getTransmission()));

        Offer offer = this.modelMapper.map(offerServiceModel, Offer.class);
        offer.setModel(this.modelService.getModelById(offerAddBindingModel.getModel()));
        offer.setSeller(this.userService.getUserById(offerAddBindingModel.getSellerId()));
        offer.setModel(this.modelService.getModelById(offerAddBindingModel.getModel()));
        offer.setCreated(new Date());
        offer.setModified(new Date());

        this.offerRepository.saveAndFlush(offer);

        return offerServiceModel;
    }

    @Override
    public Offer updateOffer(Offer offer) {
        offer.setModified(new Date());
        return this.offerRepository.save(offer);
    }

    @Override
    public Offer deleteOffer(String id) {
        Offer offer = this.getOfferById(id);
        offerRepository.deleteById(id);
        return offer;
    }

    @Override
    public long offersCount() {
        return this.offerRepository.count();
    }
}

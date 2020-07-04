package com.westcoastdealershop.models.bindingModels;

import com.westcoastdealershop.models.entities.Model;
import com.westcoastdealershop.models.entities.User;
import com.westcoastdealershop.models.entities.enums.EngineType;
import com.westcoastdealershop.models.entities.enums.TransmissionType;
import com.westcoastdealershop.models.entities.enums.VehCondition;

import java.util.Date;

public class OfferViewBindingModel {
    private String id;
    private String description;
    private EngineType engine;
    private String imageUrl;
    private Long mileage;
    private Double price;
    private TransmissionType transmission;
    private Integer year;
    private Model model;
    private User seller;
    private VehCondition vehCondition;

    public OfferViewBindingModel() {
    }


}

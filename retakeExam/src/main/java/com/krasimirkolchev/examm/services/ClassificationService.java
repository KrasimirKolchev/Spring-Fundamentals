package com.krasimirkolchev.examm.services;

import com.krasimirkolchev.examm.models.entities.Classification;
import com.krasimirkolchev.examm.models.serviceModels.ClassificationServiceModel;

import java.util.List;

public interface ClassificationService {

    List<ClassificationServiceModel> getAllClassifications();

    Classification getClassificationById(String classification);
}

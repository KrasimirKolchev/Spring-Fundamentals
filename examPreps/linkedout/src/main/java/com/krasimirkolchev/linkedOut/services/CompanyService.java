package com.krasimirkolchev.linkedOut.services;

import com.krasimirkolchev.linkedOut.models.entity.Company;
import com.krasimirkolchev.linkedOut.models.serviceModels.CompanyServiceModel;

import java.util.List;

public interface CompanyService {

    Company findCompanyById(String id);

    Company findCompanyByName(String name);

    void addCompany(CompanyServiceModel companyServiceModel);

    List<CompanyServiceModel> getAllCompanies();

    void deleteCompanyById(String id);
}

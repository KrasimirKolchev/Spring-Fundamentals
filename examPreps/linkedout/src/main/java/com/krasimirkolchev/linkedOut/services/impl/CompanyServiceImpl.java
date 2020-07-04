package com.krasimirkolchev.linkedOut.services.impl;

import com.krasimirkolchev.linkedOut.models.entity.Company;
import com.krasimirkolchev.linkedOut.models.serviceModels.CompanyServiceModel;
import com.krasimirkolchev.linkedOut.repositories.CompanyRepository;
import com.krasimirkolchev.linkedOut.services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Company findCompanyById(String id) {
        return this.companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company findCompanyByName(String name) {
        return this.companyRepository.findByName(name).orElse(null);
    }

    @Override
    public void addCompany(CompanyServiceModel companyServiceModel) {
        if (this.findCompanyByName(companyServiceModel.getName()) != null) {
            throw new EntityExistsException(String
                    .format("Company with name: %s already exist!", companyServiceModel.getName()));
        }

        this.companyRepository.save(this.modelMapper.map(companyServiceModel, Company.class));
    }

    @Override
    public List<CompanyServiceModel> getAllCompanies() {
        return this.companyRepository.findAll()
                .stream()
                .map(company -> this.modelMapper.map(company, CompanyServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCompanyById(String id) {
        this.companyRepository.deleteById(id);
    }
}

package com.jam.companyms.companies.implementation;

import com.jam.companyms.companies.Company;
import com.jam.companyms.companies.CompanyRepository;
import com.jam.companyms.companies.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImplementation implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Company updateCompany(Long companyId, Company updatedCompanyDetails) {
        Optional<Company> companyFound = companyRepository.findById(companyId);
        if(companyFound.isPresent()) {
            Company company = companyFound.get();
            updateCompanyDetails(company, updatedCompanyDetails);
            return companyRepository.save(company);
        }
        return null;
    }

    private void updateCompanyDetails(Company company, Company updatedCompanyDetails) {
        company.setName(updatedCompanyDetails.getName());
        company.setDescription(updatedCompanyDetails.getDescription());
    }
}

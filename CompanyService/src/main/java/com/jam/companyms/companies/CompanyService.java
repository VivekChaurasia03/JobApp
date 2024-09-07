package com.jam.companyms.companies;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    void createCompany(Company company);

    boolean deleteCompanyById(Long Id);

    Company updateCompany(Long companyId, Company company);
}

package com.server.demo.service;

import com.server.demo.entity.playerCompany.Company;
import com.server.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Transactional
    public Company getCompany(Long id){
        return companyRepository.findById(id).get();
    }
}

package com.server.demo.service;

import com.server.demo.entity.resource.Resources;
import com.server.demo.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Transactional
    public void saveResource(Resources resources){
        resourceRepository.save(resources);
    }

    @Transactional
    public Resources getById(Long id){
        return resourceRepository.findById(id).get();
    }
}

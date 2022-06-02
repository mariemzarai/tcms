package com.restapi.tcms.dao;

import com.restapi.tcms.model.ServiceStagiaire;
import com.restapi.tcms.repository.ServiceStagiaireRepository;
import com.restapi.tcms.service.ServiceAuth;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceStagiaireDao implements Dao<ServiceStagiaire> {
    private final ServiceStagiaireRepository serviceStagiaireRepository;
    private final ServiceAuth serviceAuth;

    public ServiceStagiaireDao(ServiceStagiaireRepository serviceStagiaireRepository, ServiceAuth serviceAuth) {
        this.serviceStagiaireRepository = serviceStagiaireRepository;
        this.serviceAuth = serviceAuth;
    }

    @Override
    public Optional<ServiceStagiaire> getById(Long id) {
        return serviceStagiaireRepository.findById(id);
    }

    @Override
    public List<ServiceStagiaire> getAll() {
        return serviceStagiaireRepository.findAll();
    }

    @Override
    public Optional<ServiceStagiaire> create(ServiceStagiaire serviceStagiaire) {
        try {
            ServiceStagiaire save = serviceStagiaireRepository.save(serviceStagiaire);
            serviceAuth.createIdentityAccount(save);
            return Optional.of(save);
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        serviceStagiaireRepository.deleteById(id);
    }
}

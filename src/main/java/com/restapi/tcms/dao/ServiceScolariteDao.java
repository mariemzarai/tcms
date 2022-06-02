package com.restapi.tcms.dao;

import com.restapi.tcms.model.ServiceScolarite;
import com.restapi.tcms.repository.ServiceScolariteRepository;
import com.restapi.tcms.service.ServiceAuth;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceScolariteDao implements Dao<ServiceScolarite>{
    private final ServiceScolariteRepository serviceScolariteRepository;
    private final ServiceAuth serviceAuth;

    public ServiceScolariteDao(ServiceScolariteRepository serviceScolariteRepository, ServiceAuth serviceAuth) {
        this.serviceScolariteRepository = serviceScolariteRepository;
        this.serviceAuth = serviceAuth;
    }

    @Override
    public Optional<ServiceScolarite> getById(Long id) {
        return serviceScolariteRepository.findById(id);
    }

    @Override
    public List<ServiceScolarite> getAll() {
        return serviceScolariteRepository.findAll();
    }

    @Override
    public Optional<ServiceScolarite> create(ServiceScolarite serviceScolarite) {
        try {
            ServiceScolarite save = serviceScolariteRepository.save(serviceScolarite);
            serviceAuth.createIdentityAccount(save);
            return Optional.of(save);
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        serviceScolariteRepository.deleteById(id);
    }
}

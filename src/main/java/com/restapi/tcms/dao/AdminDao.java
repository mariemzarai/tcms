package com.restapi.tcms.dao;

import com.restapi.tcms.model.Admin;
import com.restapi.tcms.repository.AdminRepository;
import com.restapi.tcms.service.ServiceAuth;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AdminDao implements Dao<Admin> {
    private final AdminRepository adminRepository;
    private final ServiceAuth serviceAuth;

    public AdminDao(AdminRepository adminRepository, ServiceAuth serviceAuth) {
        this.adminRepository = adminRepository;
        this.serviceAuth = serviceAuth;
    }

    @Override
    public Optional<Admin> getById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> create(Admin admin) {
        try {
            Admin save = adminRepository.save(admin);
            serviceAuth.createIdentityAccount(save);
            return Optional.of(save);
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        adminRepository.deleteById(id);
    }
}

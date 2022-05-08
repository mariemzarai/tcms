package com.restapi.tcms.security;

import com.restapi.tcms.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurDao implements Dao<Utilisateur>, UserDetailsService {
    private UtilisateurRepository utilisateurRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurDao(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<Utilisateur> getById(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Optional<Utilisateur> create(Utilisateur utilisateur) {
        if(!utilisateurRepository.existsByUsername(utilisateur.getUsername())) {
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            return Optional.of(utilisateurRepository.save(utilisateur));
        }
        else return Optional.empty();
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if(utilisateurRepository.existsById(id))
            utilisateurRepository.deleteById(id);
        else throw new EntityNotFoundException("No user with id" + id);
    }

    public Optional<Utilisateur> getByUsername(String username){
        return utilisateurRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("No user with username: " + username));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //TODO add authorities to users and add dynamically
        authorities.add(new SimpleGrantedAuthority("utilisateur"));

        return new User(utilisateur.getUsername(), utilisateur.getPassword(), authorities);
    }
}

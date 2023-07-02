package com.example.demo.service.nation;

import com.example.demo.model.Nation;
import com.example.demo.model.User;
import com.example.demo.repository.INationRepository;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NationServiceIMPL implements INationService {
    @Autowired
    private INationRepository nationRepository;
    @Autowired
    private UserDetailService userDetailService;
    @Override
    public List<Nation> findAll() {
        return nationRepository.findAll();
    }

    @Override
    public void save(Nation nation) {
        User user = userDetailService.getCurrentUser();
        nation.setUser(user);
        nationRepository.save(nation);
    }

    @Override
    public Page<Nation> findALl(Pageable pageable) {
        return nationRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        nationRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return nationRepository.existsByName(name);
    }


    @Override
    public Optional<Nation> findById(Long id) {
        return nationRepository.findById(id);
    }
}

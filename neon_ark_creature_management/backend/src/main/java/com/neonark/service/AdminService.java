package com.neonark.service;

import com.neonark.dto.UserDTO;
import com.neonark.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> getAllUsers() {

        return repository.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getRole()
                ))
                .toList();
    }
}
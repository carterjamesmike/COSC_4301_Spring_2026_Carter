package com.neonark.controller;

import com.neonark.dto.UserDTO;
import com.neonark.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {

        return ResponseEntity.ok(
                service.getAllUsers()
        );
    }
}
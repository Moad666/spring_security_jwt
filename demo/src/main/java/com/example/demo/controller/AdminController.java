package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	
	@Autowired
	AdminService adminservice;
	
	
	@PutMapping("/update")
    public ResponseEntity<String> updateUserRole(@RequestParam Long userId, 
                                                 @RequestParam String roleName) {
        adminservice.update(userId, roleName);
        return ResponseEntity.ok("User role updated");
    }
	
	@GetMapping("/findAll")
	public List<User> findAll(){
		return adminservice.findAll();
	}
	
	@GetMapping("/findById/{userId}")
	public UserDTO findById(@PathVariable Long userId){
		return adminservice.findById(userId);
	}
	
}

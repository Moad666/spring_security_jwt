package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entity.AppRole;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.RoleRepository;

@Service
public class AdminService {
	
	
	@Autowired
	AdminRepository adminrepository;
	
	@Autowired
	RoleRepository rolerepository;
	
	
	//@PreAuthorize("hasRole('ADMIN')")
	public void update(Long userId, String roleName) {
		User user = adminrepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		AppRole appRole = AppRole.valueOf(roleName);
        Role role = rolerepository.findByroleName(appRole)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        adminrepository.save(user);
		
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	public List<User> findAll(){
		return adminrepository.findAll();
	}
	
	public UserDTO findById(Long userId){
		//return adminrepository.findById(userId);
		User user = adminrepository.findById(userId).orElseThrow();
        return convertToDto(user);
	}
	private UserDTO convertToDto(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.isAccountNonLocked(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.getTwoFactorSecret(),
                user.isTwoFactorEnabled(),
                user.getSignUpMethod(),
                user.getRole(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }
	
	public User findByUsername(String username) {
        Optional<User> user = adminrepository.findByuserName(username);
        return user.orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }
	
}

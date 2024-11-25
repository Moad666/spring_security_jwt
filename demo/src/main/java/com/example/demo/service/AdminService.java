package com.example.demo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entity.AppRole;
import com.example.demo.entity.PasswordResetToken;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.PasswordResetTokenRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.util.EmailService;

@Service
public class AdminService {
	
	
	@Autowired
	AdminRepository adminrepository;
	
	@Autowired
	RoleRepository rolerepository;
	
	@Autowired 
	PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Value("${frontend.url}")
	String frontendUrl;
	
	@Autowired
	EmailService emailService;
	
	
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
	
	public void generatePasswordResetToken(String email){
        User user = adminrepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();
        Instant expiryDate = Instant.now().plus(24, ChronoUnit.HOURS);
        PasswordResetToken resetToken = new PasswordResetToken(token, expiryDate, user);
        passwordResetTokenRepository.save(resetToken);

        String resetUrl = frontendUrl + "/reset-password?token=" + token;
        // Send email to user
        emailService.sendPasswordResetEmail(user.getEmail(), resetUrl);
    }
	
}

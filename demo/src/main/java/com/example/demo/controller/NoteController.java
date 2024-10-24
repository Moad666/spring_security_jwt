package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Note;
import com.example.demo.service.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {
	
	@Autowired
	NoteService noteservice;
	
	@PostMapping("/save")
	public void save(@RequestBody Note note) { //@AuthenticationPrincipal UserDetails userDetails
		noteservice.save(note);
	}
	
	@GetMapping("/all")
	public List<Note> findAll(){
		return noteservice.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public Optional<Note> findById(@PathVariable Long id){
		return noteservice.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		noteservice.delete(id);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable Long id, @RequestBody Note note) {
		noteservice.update(id, note);
	}
	
	@GetMapping("/findByownerUsername/{ownerUsername}")
	public List<Note> findByownerUsername(@PathVariable String ownerUsername){
		return noteservice.findByownerUsername(ownerUsername);
	}
	
	/*@PostMapping("/create")
    public Note createNote(@RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println("USER DETAILS: " + username);
        return noteservice.createNoteForUser(username, content);
    }*/
}










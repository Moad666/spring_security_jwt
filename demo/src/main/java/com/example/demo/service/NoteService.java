package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Note;
import com.example.demo.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	NoteRepository noterepository;
	
	public void save(Note note) {
		noterepository.save(note);
	}
	
	public Optional<Note> findById(Long id) {
		return noterepository.findById(id);
	}
	
	public List<Note> findAll(){
		return noterepository.findAll();
	}
	
	public void delete(Long id) {
		noterepository.deleteById(id);
	}
	
	public void update(Long id, Note note) {
		Optional<Note> notes = noterepository.findById(id);
		if(notes.isPresent()) {
			Note existingNote = notes.get();
			
			existingNote.setContent(note.getContent());
			existingNote.setOwnerUsername(note.getOwnerUsername());
			
			noterepository.save(existingNote);
		}else {
			System.out.println("Note with ID " + id + " not found.");
		}
	}
	
	public List<Note> findByownerUsername(String ownerUsername) {
        return noterepository.findByownerUsername(ownerUsername);
    }
	
	
    /*public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        Note savedNote = noterepository.save(note);
        return savedNote;
    }*/
}

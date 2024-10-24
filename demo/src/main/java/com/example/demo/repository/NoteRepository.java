package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
	
	List<Note> findByownerUsername(String ownerUsername);
}

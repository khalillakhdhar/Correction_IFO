package com.j2.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.j2.exception.ResourceNotFoundException;

import com.j2.model.Projet;
import com.j2.model.repository.ProjetRepository;

@RestController
public class ProjetController {

	@Autowired
	ProjetRepository projetRepository;
	@GetMapping("projets")
	public List<Projet> getAllProjets()
	{
		return (List<Projet>) projetRepository.findAll();
		
		
	}
	@PostMapping("projets")
	public Projet addProjet(@Valid @RequestBody Projet projet)
	{
		
		return projetRepository.save(projet);
		
	}
	// Get a Single Utilisateur
	@GetMapping("/projets/{id}")
	public Projet getProjetById(@PathVariable(value = "id") Long projetId) {
	    return projetRepository.findById(projetId)
	            .orElseThrow(() -> new ResourceNotFoundException("projet", "id", projetId));
	}
	  
	// Delete a Utilisateur
	@DeleteMapping("/projets/{id}")
	public ResponseEntity<?> deleteProjet(@PathVariable(value = "id") Long projetId) {
	    Projet projet = projetRepository.findById(projetId)
	            .orElseThrow(() -> new ResourceNotFoundException("projet", "id", projetId));

	    projetRepository.delete(projet);

	    return ResponseEntity.ok().build();
	}
	
	
}

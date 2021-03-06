package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Commentaire;
import com.example.demo.model.CommentaireId;
import com.example.demo.model.User;
import com.example.demo.repo.CommentaireIdRepo;
import com.example.demo.repo.CommentaireRepo;

@RestController
public class CommentaireController {
	
	@Autowired
	CommentaireRepo comrep;
	
	@Autowired
	CommentaireIdRepo comidrep;
	
	
	
	@GetMapping("/commentaire/{Idproduit}")
	public Commentaire getUser(@PathVariable("Idproduit") int Idproduit) {
		
	CommentaireId com=	comidrep.getOne(Idproduit);	
	return comrep.getOne(com.getProduitId());
	}
	
	@GetMapping("/commentaires")
	public List<Commentaire> getAllUsers() {

		return comrep.findAll();
	}
	
	@PostMapping("/commentaires")
	public Commentaire newCommentaire(@RequestBody Commentaire commentaire ) {
		

		return comrep.save(commentaire) ;
	}
	

}

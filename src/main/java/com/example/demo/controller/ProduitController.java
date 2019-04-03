package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Produit;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProduitRepository;

@RestController()
public class ProduitController {
	
	public List<Produit> produits = new ArrayList<>();
	
	@Autowired
	ProduitRepository produitRepository;
	
	@Autowired
	CategoryRepository catRepository;


	
	
	@GetMapping("/produits")
	public List<Produit> getAllProduits() {

		return produitRepository.findAll();
	}
	
	@GetMapping("/produit/{Id}")
	public Produit getProduit(@PathVariable("Id") int produitId) {
		
		return	produitRepository.getOne(produitId);
	}
	
	@DeleteMapping("/produit/d/{Id}")
	public void deletProduit(@PathVariable("Id") int produitId) {
		if(!produitRepository.existsById(produitId)) {
            throw new ResourceNotFoundException("userId " + produitId + " not found");
        }
		produitRepository.deleteById(produitId);
		

	}
	
	@PostMapping("/produit/new")
	public Produit createuser(@RequestBody Produit produit) {

		produitRepository.save(produit);
		return produit;
	}
	
	@PutMapping("/produit/edit/{Id}")
	public Produit updateproduit(@PathVariable("Id") int produitId, @RequestBody Produit newProduit) {

		
		if(!produitRepository.existsById(produitId)) {
            throw new ResourceNotFoundException("userId " + produitId + " not found");
        }
		Produit p ;
      p=  produitRepository.getOne(produitId);
      
    		 
            p.setDescription(newProduit.description);
            p.setPrix(newProduit.prix);
            p.setStock(newProduit.stock);
        	p.setImage(newProduit.image);
        	p.setNom(newProduit.nom);
        	p.setId(produitId);
        	if(p.getCategory().getId_commande()!=newProduit.getCategory().getId_commande()) {
        		p.deletecat(catRepository.getOne(p.getCategory().getId_commande()));
        		catRepository.getOne(newProduit.getCategory().getId_commande()).addproduct(p);
        		
        	}
        	
        	
        	return produitRepository.save(p);

	}


	
	
	
	
}

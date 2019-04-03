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

import com.example.demo.model.Category;
import com.example.demo.model.Produit;
import com.example.demo.model.User;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProduitRepository;

@RestController
public class CategoryController {
	public List<Category> categories = new ArrayList<>();
	public List<Produit> produits = new ArrayList<>();

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProduitRepository produitrepository;

	// afficher un seul user
	@GetMapping("/category/{Id}")
	public Category getcategory(@PathVariable("Id") int categoryId) {
          return categoryRepository.getOne(categoryId);

		}

	// show all
	@GetMapping("/categories")
	public List<Category> getAllcategories() {

		return categoryRepository.findAll();
	}

	// create
	@PostMapping("/addcategory")
	public Category createcategory(@RequestBody Category category) {

		categoryRepository.save(category);
		return category;
	}

	// edit
	@PutMapping("/category/{Id}")
	public Category updateUser(@PathVariable("Id") int categoryId, @RequestBody Category newcategory) {

		if(!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("userId " + categoryId + " not found");
        }
		Category cat ;
      cat=  categoryRepository.getOne(categoryId);
    		 
        	cat.setName(newcategory.getName());
        	cat.setDescription(newcategory.getDescription());
        	cat.setId_commande(categoryId);
        	return categoryRepository.save(cat);
        }

	

	// delete
	@DeleteMapping("/category/d/{Id}")
	public void deleteUser(@PathVariable("Id") int categoryId) {
		if(!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("userId " + categoryId + " not found");
        }
		categoryRepository.deleteById(categoryId);

	}

	@GetMapping("/pc/{idcat}/{idprod}")
	public Produit addproduitocat(@PathVariable("idcat") int categoryId, @PathVariable("idprod") int produitId) {
		Category cat = new Category();
		Produit prod = new Produit();
		categories = categoryRepository.findAll();
		int var = produitId;

		for (Category c : categories) {
			if (c.getId_commande() == categoryId) {
				cat = c;
			}
		}

		produits = produitrepository.findAll();
		

		for (Produit p : produits) {
			if (p.id == produitId) {
				p.deletecat(p.getCategory());
				
				cat.addproduct(p);
				produitrepository.save(p);
				prod=p;
				
			}
			
		}
		
		return prod;

	}

}

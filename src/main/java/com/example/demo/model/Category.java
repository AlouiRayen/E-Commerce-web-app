package com.example.demo.model;


import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id_commande;
	
	public String name;
		public String description;
	
		@JsonIgnore
	 @OneToMany(mappedBy = "Category", cascade = CascadeType.MERGE  )
	    private Set<Produit> produits;
	 
	 public Category(String name,String desc, Produit... produits) {
	        this.name= name;
	        this.description=desc;
	        this.produits = Stream.of(produits).collect(Collectors.toSet());
	        this.produits.forEach(x -> x.setCategory(this));
	    }

	public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Produit> getProduits() {
		return produits;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}
	
	public void addproduct(Produit produit) {
		produits.add(produit);
		produit.setCategory(this);
		
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
}

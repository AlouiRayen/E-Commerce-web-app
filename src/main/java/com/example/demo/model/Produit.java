package com.example.demo.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Produit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	
	public String nom;
	public String image;
	public double prix;
	public int stock;
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String description;
	
	@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires = new HashSet<>();
	
	@ManyToOne
    @JoinColumn
    private Category Category;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Category getCategory() {
		return Category;
	}
	public void setCategory(Category bookCategory) {
		this.Category = bookCategory;
	}
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Produit(String nom, String image,String desc,double prix,int stock) {
		this.nom = nom;
		this.image = image;
		this.description=desc;
		this.prix=prix;
		this.stock=stock;
		
	}
	
	public void deletecat(Category cat) {
		this.Category=null;
		cat.getProduits().remove(this);
	}
	
	
	
	
	
	

}

package com.example.demo.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Entity
public class Commentaire {
 
    @EmbeddedId
    private CommentaireId id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("produitId")
    private Produit produit;
 
    @Column(name = "created_on")
    private Date createdOn = new Date();
    
    @Column(name="contenu")
    private String contenu;
 
    private Commentaire() {}
 
    public Commentaire(User user, Produit produit) {
        this.user = user;
        this.produit = produit;
        this.id = new CommentaireId(user.getId(), produit.getId());
    }
 
    //Getters and setters omitted for brevity
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Commentaire that = (Commentaire) o;
        return Objects.equals(user, that.user) &&
               Objects.equals(produit, that.produit);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(user, produit);
    }
}

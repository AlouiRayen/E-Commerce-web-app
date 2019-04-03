package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommentaireId
    implements Serializable {
 
    @Column(name = "user_id")
    private int userId;
 
    @Column(name = "produit_id")
    private int produitId;
 
    private CommentaireId() {}
 
    public CommentaireId(
        int userId,
        int produitId) {
        this.userId = userId;
        this.produitId = produitId;
    }
 
    //Getters omitted for brevity
 
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProduitId() {
		return produitId;
	}

	public void setProduitId(int produitId) {
		this.produitId = produitId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CommentaireId that = (CommentaireId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(produitId, that.produitId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(userId, produitId);
    }
}
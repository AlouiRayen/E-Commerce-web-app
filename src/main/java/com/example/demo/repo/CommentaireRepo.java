package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Commentaire;

public interface CommentaireRepo extends JpaRepository<Commentaire, Integer> {

	@Override
	default <S extends Commentaire> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Optional<Commentaire> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

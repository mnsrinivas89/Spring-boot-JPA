package com.verizontraining.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.verizontraining.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
    public List<Movie> findByRatingGreaterThanEqual(Double rating);
    public List<Movie> findByHero(String hero);
}

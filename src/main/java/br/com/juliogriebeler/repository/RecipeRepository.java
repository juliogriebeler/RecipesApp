package br.com.juliogriebeler.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.juliogriebeler.model.Recipe;

public interface RecipeRepository extends MongoRepository<Recipe, String>{
	
	@Query("{isVegetarian : ?0}")
	public List<Recipe> findByIsVegetarian(Boolean isVegetarian);
}

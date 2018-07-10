/**
 * 
 */
package br.com.juliogriebeler.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.juliogriebeler.exception.RecipeException;
import br.com.juliogriebeler.model.Recipe;
import br.com.juliogriebeler.repository.RecipeRepository;

/**
 * @author juliofg
 *
 */

@RestController
@RequestMapping("/api")
public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;

	@PostMapping("/recipe")
	@CrossOrigin
	public Recipe save(@Valid @RequestBody Recipe recipe, @RequestParam("dishImage") MultipartFile dishImage) {
		if (dishImage.isEmpty()) {
			System.out.println("EMPTY IMAGE");
		} else {
			byte[] bytes;
			String fileName = dishImage.getOriginalFilename();
			try {
				bytes = dishImage.getBytes();
				Path path = Paths.get(fileName);
				Files.write(path, bytes);
				recipe.setDishImage(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return recipeRepository.save(recipe);
	}

	@GetMapping("/recipes")
	@ResponseBody
	@CrossOrigin
	public List<Recipe> findAll(Model model) {
		if(model.containsAttribute("isVegetarian")) {
			System.out.println("IS VEG");
			return recipeRepository.findByIsVegetarian(Boolean.TRUE);
		}
		System.out.println("NOT VEG");
		return recipeRepository.findAll();
	}

	@GetMapping("/recipe/{id}")
	@ResponseBody
	@CrossOrigin
	public Recipe findById(@PathVariable(value = "id") String recipeId) {
		return recipeRepository.findById(recipeId)
				.orElseThrow(() -> new RecipeException("Recipe", "id", recipeId));
	}
	
	@DeleteMapping("/recipe/{id}")
	@ResponseBody
	public String delete(@PathVariable(value = "id") String recipeId) {
		try {
			recipeRepository.deleteById(recipeId);
		} catch (Exception e) {
			return "Error deleting recipe with ID " + recipeId;
		}
		return "Recipe deleted!";
	}
}
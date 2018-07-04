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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.juliogriebeler.exception.ResourceNotFoundException;
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
	public Recipe createRecipe(@Valid @RequestBody Recipe recipe, @RequestParam("image") MultipartFile recipeImage) {
		if (recipeImage.isEmpty()) {
			System.out.println("EMPTY IMAGE");
		} else {
			byte[] bytes;
			String fileName = recipeImage.getOriginalFilename();
			try {
				bytes = recipeImage.getBytes();
				Path path = Paths.get(fileName);
				Files.write(path, bytes);
				recipe.setDishImage(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return recipeRepository.save(recipe);
	}

	@GetMapping("/recipe")
	@ResponseBody
	public List<Recipe> getRecipes(Model model) {
		if(model.containsAttribute("isVegetarian")) {
			return recipeRepository.findByIsVegetarian(Boolean.TRUE);
		}
		return recipeRepository.findAll();
	}

	@GetMapping("/recipe/{id}")
	@ResponseBody
	public Recipe getRecipeById(@PathVariable(value = "id") String recipeId) {
		return recipeRepository.findById(recipeId)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));
	}
}
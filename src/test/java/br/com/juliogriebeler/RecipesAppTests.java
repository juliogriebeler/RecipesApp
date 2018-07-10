package br.com.juliogriebeler;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.juliogriebeler.model.CookingInstruction;
import br.com.juliogriebeler.model.Ingredient;
import br.com.juliogriebeler.model.MeasureUnit;
import br.com.juliogriebeler.model.Recipe;
import br.com.juliogriebeler.repository.RecipeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipesAppTests {

	@MockBean
	RecipeRepository recipeRepositoryMock;
	
	@Test
	public void saveRecipeTest(){
		Recipe r = createTestRecipe();
		assertEquals(recipeRepositoryMock.save(r), r);
	}
	

	private Recipe createTestRecipe(){
		Recipe r = new Recipe();

		r.setName("Recipe name");
		r.setDescription("Recipe Description");
		r.setDateCreation(new Date());
		//r.setDishImage("");
		r.setDishSuitableQty(3);
		r.setIsVegetarian(false);
		
		List<CookingInstruction> cookingInstructions = new ArrayList<CookingInstruction>();
		CookingInstruction c1 = new CookingInstruction();
		c1.setPosition(1);
		c1.setDescription("Add salt");
		cookingInstructions.add(c1);
		CookingInstruction c2 = new CookingInstruction();
		c1.setPosition(2);
		c1.setDescription("Add pepper powder");
		cookingInstructions.add(c2);
		r.setCookingInstructions(cookingInstructions);
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Ingredient i1 = new Ingredient();
		i1.setDescription("Coffee");
		i1.setName("Coffee");
		i1.setMeasureType(MeasureUnit.CUP);
		i1.setQuantity(new Double(2));
		ingredients.add(i1);
		Ingredient i2 = new Ingredient();
		i2.setDescription("Cold");
		i2.setName("Milk");
		i2.setMeasureType(MeasureUnit.LITER);
		i2.setQuantity(new Double(0.3));
		ingredients.add(i2);
		r.setIngredients(ingredients);
		
		return r;
	}

}

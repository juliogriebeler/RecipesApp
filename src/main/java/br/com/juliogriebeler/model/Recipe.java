package br.com.juliogriebeler.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="recipes")
public class Recipe {

	@Id
	private String id;
	private String name;
	private String description;	
	private Date dateCreation;
	private String dishImage;
	private Boolean isVegetarian;
	private int dishSuitableQty;
	private List<Ingredient> ingredients;
	private List<CookingInstruction> cookingInstructions;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getDishImage() {
		return dishImage;
	}
	public void setDishImage(String dishImage) {
		this.dishImage = dishImage;
	}
	public Boolean getIsVegetarian() {
		return isVegetarian;
	}
	public void setIsVegetarian(Boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}
	public int getDishSuitableQty() {
		return dishSuitableQty;
	}
	public void setDishSuitableQty(int dishSuitableQty) {
		this.dishSuitableQty = dishSuitableQty;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public List<CookingInstruction> getCookingInstructions() {
		return cookingInstructions;
	}
	public void setCookingInstructions(List<CookingInstruction> cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}
}
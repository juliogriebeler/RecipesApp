package br.com.juliogriebeler.model;

/**
 * @author juliofg
 *
 */
public class Ingredient {

	private String name;
	private String description;	
	private Double quantity;
	private MeasureUnit measureType;
	
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
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public MeasureUnit getMeasureType() {
		return measureType;
	}
	public void setMeasureType(MeasureUnit measureType) {
		this.measureType = measureType;
	}
	
	
}
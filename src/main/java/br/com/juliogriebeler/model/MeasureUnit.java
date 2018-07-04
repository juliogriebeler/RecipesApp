/**
 * 
 */
package br.com.juliogriebeler.model;

/**
 * @author juliofg
 *
 */
public enum MeasureUnit {
	SPON("Spon"),
	CUP("Cup"),
	LITER("Liter");
	
   private String unit;

   public String getUnit() {
	   return this.unit;
   }

   MeasureUnit(String unit) {
	   this.unit = unit;
   }
	
}

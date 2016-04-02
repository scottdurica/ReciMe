package com.emrox_riprap.recime.models;

/**
 * Created by scott on 12/3/2015.
 */
public class Recipe {

    private int id;
    private String name;
    private String directions;
    private int cookTime;
    private int ovenTemp;
    private String servings;
    private String summary;
    private int prepTime;
    private String category;

    public Recipe() {
    }

    public Recipe(String name, String directions, int cookTime, int ovenTemp, String servings, String summary, int prepTime, String category) {
        this.name = name;
        this.directions = directions;
        this.cookTime = cookTime;
        this.ovenTemp = ovenTemp;
        this.servings = servings;
        this.summary = summary;
        this.prepTime = prepTime;
        this.category = category;
    }

    public Recipe(int id, String name, String directions, int cookTime, int ovenTemp, String servings, String summary, int prepTime) {
        this.id = id;
        this.name = name;
        this.directions = directions;
        this.cookTime = cookTime;
        this.ovenTemp = ovenTemp;
        this.servings = servings;
        this.summary = summary;
        this.prepTime = prepTime;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getOvenTemp() {
        return ovenTemp;
    }

    public void setOvenTemp(int ovenTemp) {
        this.ovenTemp = ovenTemp;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
}

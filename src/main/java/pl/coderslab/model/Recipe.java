package pl.coderslab.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Recipe {
    private int id;
    private String name;
    private String ingredients;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    private int preparation_time;
    private String preparation;

    public Recipe(String name, String ingredients, String description, int preparation_time, String preparation) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.preparation_time = preparation_time;
        this.preparation = preparation;
    }

    public Recipe() {
    }

    public Recipe(int id, String name, String ingredients, String description, LocalDateTime created, LocalDateTime updated, int preparation_time, String preparation) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.preparation_time = preparation_time;
        this.preparation = preparation;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public int getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(int preparation_time) {
        this.preparation_time = preparation_time;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", preparation_time=" + preparation_time +
                ", preparation='" + preparation + '\'' +
                '}';
    }
}

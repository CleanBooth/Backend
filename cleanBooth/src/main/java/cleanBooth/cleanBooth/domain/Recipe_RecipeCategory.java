package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Recipe_RecipeCategory {

    @Id @GeneratedValue
    @Column(name = "RECIPE_RECIPECATEGORY")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private List<RecipeCategory> recipeCategories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private List<Recipe> recipes = new ArrayList<>();
}

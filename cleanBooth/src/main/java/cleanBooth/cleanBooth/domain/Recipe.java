package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Recipe {

    @Id @GeneratedValue
    @Column(name = "RECIPE_ID")
    private Long id;

    private String name;
    private String style;
    private String ingredients;

    private URL link;

    private String writer;

    @Enumerated(EnumType.STRING)
    private Site site;

    @OneToMany(mappedBy = "RecipeCategory")
    private List<Recipe_RecipeCategory> recipe_recipeCategories = new ArrayList<>();


}

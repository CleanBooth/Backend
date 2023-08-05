package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class RecipeCategory {

    @Id @GeneratedValue
    @Column(name = "RECIPE_CATEGORY_ID")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe_RecipeCategory recipe_recipeCategory;

}

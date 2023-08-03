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

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe_RecipeCategory recipe_recipeCategory;


}

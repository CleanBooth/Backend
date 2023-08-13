package cleanBooth.cleanBooth.Recipe;

import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.Site;
import lombok.Getter;
import lombok.Setter;


import java.net.URL;
import java.util.List;

@Getter @Setter
public class RecipeDto {

    private String name;
    private String styles;
    private String ingredients;
    private String link;
    private Site site;

    public RecipeDto(Recipe recipe){
        this.name = recipe.getName();
        this.styles = recipe.getStyles();
        this.ingredients = recipe.getIngredients();
        this.link = recipe.getLink();
        this.site = recipe.getSite();
    }

}

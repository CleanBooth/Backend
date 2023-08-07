package cleanBooth.cleanBooth.Recipe;

import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.Site;
import lombok.Getter;
import lombok.Setter;


import java.net.URL;
@Getter @Setter
public class RecipeDto {

    private String name;
    private String style;
    private String ingredients;
    private URL link;
    private String writer;
    private Site site;

    public RecipeDto(Recipe recipe){
        this.name = recipe.getName();
        this.style = recipe.getStyle();
        this.ingredients = recipe.getIngredients();
        this.link = recipe.getLink();
        this.writer = recipe.getWriter();
        this.site = recipe.getSite();
    }

}

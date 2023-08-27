package cleanBooth.cleanBooth.Item.Dto;

import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.Site;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemRecipeDto {

    private String name;
    private String styles;
    private String link;
    private Site site;

    public ItemRecipeDto(Recipe recipe) {
        this.name = recipe.getName();
        this.styles = recipe.getStyles();
        this.link = recipe.getLink();
        this.site = recipe.getSite();
    }
}

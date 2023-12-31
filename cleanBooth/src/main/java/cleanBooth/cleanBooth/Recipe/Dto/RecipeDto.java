package cleanBooth.cleanBooth.Recipe.Dto;

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
    private String link;
    private Site site;
    private String videoTitle;
    private Boolean isLike;
    private String image;

    public RecipeDto(Recipe recipe) {
        this.name = recipe.getName();
        this.styles = recipe.getStyles();
        this.link = recipe.getLink();
        this.site = recipe.getSite();
        this.videoTitle = recipe.getVideoTitle();
        this.isLike = recipe.getIsLike();
        this.image = recipe.getImage();
    }
}

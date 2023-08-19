package cleanBooth.cleanBooth.Recipe.Dto;

import cleanBooth.cleanBooth.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecipeIdDto {
    private String link;
    private String name;
    private Boolean isLiked;
    private String videoTitle;

    public RecipeIdDto(Recipe recipe){
        this.name = recipe.getName();
        this.link = recipe.getLink();
        this.isLiked = recipe.getIsLike();
        this.videoTitle = recipe.getVideoTitle();
    }
}

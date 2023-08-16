package cleanBooth.cleanBooth.Recipe.Dto;

import cleanBooth.cleanBooth.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecipeFilterDto {

    private String name;
    private String writer;
    private Boolean isLiked;

    public RecipeFilterDto(Recipe recipe){
        this.name = recipe.getName();
        this.writer = recipe.getRecipeWriter().getName();
        this.isLiked = recipe.getIsLike();
    }
}

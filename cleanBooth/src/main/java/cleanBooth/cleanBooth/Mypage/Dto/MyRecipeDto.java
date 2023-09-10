package cleanBooth.cleanBooth.Mypage.Dto;

import cleanBooth.cleanBooth.domain.Recipe;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MyRecipeDto {
    private Long recipeId;
    private String name;
    private String image;

    public MyRecipeDto(Recipe recipe){
        this.recipeId = recipe.getId();
        this.name = recipe.getName();
        this.image = recipe.getImage();
    }
}

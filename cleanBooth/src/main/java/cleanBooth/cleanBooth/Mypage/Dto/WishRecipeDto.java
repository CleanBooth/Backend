package cleanBooth.cleanBooth.Mypage.Dto;

import cleanBooth.cleanBooth.Mypage.Response.WishRecipeResponse;
import cleanBooth.cleanBooth.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WishRecipeDto {

    private String name;
    private String image;

    public WishRecipeDto(Recipe recipe){
        this.name = recipe.getName();
        this.image = recipe.getImage();
    }
}

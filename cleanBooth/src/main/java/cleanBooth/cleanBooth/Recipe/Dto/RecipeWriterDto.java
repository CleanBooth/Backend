package cleanBooth.cleanBooth.Recipe.Dto;

import cleanBooth.cleanBooth.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecipeWriterDto {

    private String link;
    private String video_title;

    public RecipeWriterDto(Recipe recipe){
        this.link = recipe.getLink();
        this.video_title = recipe.getVideoTitle();
    }
}

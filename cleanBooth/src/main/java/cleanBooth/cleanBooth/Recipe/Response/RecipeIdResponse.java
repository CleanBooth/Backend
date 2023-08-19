package cleanBooth.cleanBooth.Recipe.Response;

import cleanBooth.cleanBooth.Recipe.Dto.RecipeIdDto;
import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.RecipeWriter;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecipeIdResponse {
    private RecipeIdDto recipeIdDto;
    private RecipeWriter recipeWriter;
}

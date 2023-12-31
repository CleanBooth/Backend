package cleanBooth.cleanBooth.Recipe.Response;

import cleanBooth.cleanBooth.Recipe.Dto.RecipeFilterDto;
import cleanBooth.cleanBooth.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RecipeListResponse {
    private int totalCount;
    private List<RecipeFilterDto> recipeFilterDtos;
}

package cleanBooth.cleanBooth.Recipe.Response;

import cleanBooth.cleanBooth.Recipe.Dto.RecipeWriterDto;
import cleanBooth.cleanBooth.domain.RecipeWriter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RecipeWriterResponse {
    private List<RecipeWriterDto> recipeWriterDto;
    private RecipeWriter recipeWriter;
}

package cleanBooth.cleanBooth.Recipe;

import cleanBooth.cleanBooth.domain.RecipeWriter;

public class RecipeWriterDto {

    private String name;
    private String link;

    public RecipeWriterDto(RecipeWriter writer){
        this.name = writer.getName();
        this.link = writer.getLink();
    }
}

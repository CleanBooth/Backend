package cleanBooth.cleanBooth.Mypage.Dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MyRecipeListDto {

    private List<MyRecipeDto> Recipe = new ArrayList<>();
    private int totalCount;

    public MyRecipeListDto(List<MyRecipeDto> Recipe){
        this.Recipe = Recipe;
        this.totalCount = Recipe.size();
    }

}

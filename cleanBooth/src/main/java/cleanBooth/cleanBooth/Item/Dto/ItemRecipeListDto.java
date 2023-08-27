package cleanBooth.cleanBooth.Item.Dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemRecipeListDto {
    private List<ItemRecipeDto> recipeList;

    public ItemRecipeListDto(List<ItemRecipeDto> newDtos){
        recipeList = newDtos;
    }
}

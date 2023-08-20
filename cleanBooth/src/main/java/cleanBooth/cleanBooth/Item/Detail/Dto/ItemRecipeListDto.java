package cleanBooth.cleanBooth.Item.Detail.Dto;

import java.util.List;

public class ItemRecipeListDto {
    private List<ItemRecipeDto> recipeDtoList;

    public ItemRecipeListDto(List<ItemRecipeDto> newDtos){
        recipeDtoList = newDtos;
    }
}

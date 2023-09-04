package cleanBooth.cleanBooth.Item.Dto;

import cleanBooth.cleanBooth.domain.Item;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemDetailResponseDto {

    private ItemDetailDto item;
    private ItemRecipeListDto recipes;
    private ItemReviewListDto reviews;

    public ItemDetailResponseDto(ItemDetailDto item, List<ItemReviewDto> itemReviewDtos, List<ItemRecipeDto> itemRecipeDtos){
        this.item = item;
        this.recipes = new ItemRecipeListDto(itemRecipeDtos);
        this.reviews = new ItemReviewListDto(itemReviewDtos);
    }

}

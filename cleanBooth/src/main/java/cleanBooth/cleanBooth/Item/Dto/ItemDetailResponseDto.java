package cleanBooth.cleanBooth.Item.Dto;

import cleanBooth.cleanBooth.domain.Item;

import java.util.List;

public class ItemDetailResponseDto {

    private ItemDetailDto item;
    private ItemRecipeListDto itemRecipes;
    private ItemReviewListDto itemReviews;

    public ItemDetailResponseDto(Item item, List<ItemReviewDto> itemReviewDtos, List<ItemRecipeDto> itemRecipeDtos){
        this.item = new ItemDetailDto(item);
        this.itemRecipes = new ItemRecipeListDto(itemRecipeDtos);
        this.itemReviews = new ItemReviewListDto(itemReviewDtos);
    }

}

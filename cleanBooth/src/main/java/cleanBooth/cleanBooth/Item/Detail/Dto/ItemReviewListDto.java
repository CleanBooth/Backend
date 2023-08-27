package cleanBooth.cleanBooth.Item.Detail.Dto;

import java.util.List;

public class ItemReviewListDto {

    public List<ItemReviewDto> itemReviewDtoList;

    public ItemReviewListDto(List<ItemReviewDto> itemReviewDtos){
        this.itemReviewDtoList = itemReviewDtos;
    }
}

package cleanBooth.cleanBooth.Item.Dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemReviewListDto {

    public List<ItemReviewDto> reviewList;

    public ItemReviewListDto(List<ItemReviewDto> itemReviewDtos){
        this.reviewList = itemReviewDtos;
    }
}

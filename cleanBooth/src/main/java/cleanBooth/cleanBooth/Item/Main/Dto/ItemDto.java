package cleanBooth.cleanBooth.Item.Main.Dto;

import cleanBooth.cleanBooth.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;

@Getter
@NoArgsConstructor
public class ItemDto {

    private Long item_id;
    private String name;
    private String brandName;
    private String image;
    private Float rating;
    private Long reviewCount;


    public ItemDto(Item item){
        this.item_id = item.getId();
        this.name = item.getName();
        this.brandName = item.getBrandName();
        this.image = item.getImage();
        this.rating = item.getAvgRating();
        this.reviewCount = item.getReviewCount();
    }


}

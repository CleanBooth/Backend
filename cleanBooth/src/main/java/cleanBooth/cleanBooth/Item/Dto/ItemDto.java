package cleanBooth.cleanBooth.Item.Dto;

import cleanBooth.cleanBooth.domain.*;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class ItemDto {

    private Long itemId;
    private String name;
    private String brandName;
    private String image;
    private Float rating;
    private Long reviewCount;
    private Boolean isLiked;

    public ItemDto(Item item){
        this.itemId = item.getId();
        this.name = item.getName();
        this.brandName = item.getBrandName();
        this.image = item.getImage();
        this.rating = item.getAvgRating();
        this.reviewCount = item.getReviewCount();
    }

    public void saveIsLiked(Optional<WishItem> wishItem){
        if (wishItem.isEmpty()){
            this.isLiked = null;
        }
        else {this.isLiked = true;}
    }
}

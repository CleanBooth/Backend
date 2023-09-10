package cleanBooth.cleanBooth.Item.Dto;

import cleanBooth.cleanBooth.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class ItemDetailDto {

    private Long itemId;
    private String name;
    private String brandName;
    private Nutrient nutrient;
    private Integer price;
    private String image;
    private Double avgRating;
    private String orderLink;
//    private Boolean isCleaned;
//    private Boolean isSuperior;
    private Boolean cleanPremium;
    private String allergyInfo;
    private String ingredientInfo;
    private Long reviewCount;
    private Boolean isLiked;

    public ItemDetailDto(Item item){
        this.itemId = item.getId();
        this.name = item.getName();
        this.brandName = item.getBrandName();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.orderLink = item.getOrderLink();
        this.avgRating = (item.getReviewCount()==0)?0:item.getAvgRating();
        this.allergyInfo = item.getAllergyInfo();
        this.ingredientInfo = item.getIngredientInfo();
        this.nutrient = item.getNutrientInfo();
//        this.isCleaned = true;
//        this.isSuperior = (item.getAvgRating() < 3.0)?false:true;
        this.cleanPremium = (item.getAvgRating() > 3.5)?true:false;
        this.reviewCount = item.getReviewCount();
    }

    public void saveIsLiked(Optional<WishItem> wishItem){
        if (wishItem.isEmpty()){
            this.isLiked = false;
        }
        else {this.isLiked = true;}
    }
}

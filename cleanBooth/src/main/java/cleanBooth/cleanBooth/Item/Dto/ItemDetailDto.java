package cleanBooth.cleanBooth.Item.Dto;

import cleanBooth.cleanBooth.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemDetailDto {

    private Long itemId;
    private String name;
    private String brandName;
    private Nutrient nutrient;
    private Integer price;
    private String image;
    private Float avgRating;
    private String orderLink;
    private Category category;
    private Boolean isCleaned;
    private Boolean isSuperior;
    private Boolean cleanPremium;
    private String allergyInfo;
    private String ingredientInfo;

    public ItemDetailDto(Item item){
        this.itemId = item.getId();
        this.name = item.getName();
        this.brandName = item.getBrandName();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.orderLink = item.getOrderLink();
        this.category = item.getCategory();
        this.avgRating = (item.getReviewCount()==0)?0:item.getAvgRating();
        this.nutrient = item.getNutrientInfo();
        this.allergyInfo = item.getAllergyInfo();
        this.ingredientInfo = item.getIngredientInfo();

        this.isCleaned = true;
        this.isSuperior = (item.getAvgRating() < 3.0)?false:true;
        this.cleanPremium = (item.getAvgRating() > 3.5)?true:false;
    }
}

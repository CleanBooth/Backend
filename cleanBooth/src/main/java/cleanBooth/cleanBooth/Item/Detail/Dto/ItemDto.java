package cleanBooth.cleanBooth.Item.Detail.Dto;

import cleanBooth.cleanBooth.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;

@Getter
@NoArgsConstructor
public class ItemDto {

    private String name;
    private String brandName;
    private String description;
    private String nutrient;
    private Integer price;
    private String image;
    private NewStatus isNew;
    private TestingStatus isTesting;
    private Float testerRate;
    private String orderLink;
    private Category category;

    public ItemDto(Item item){
        this.name = item.getName();
        this.brandName = item.getBrandName();
        this.description = item.getDescription();
        this.nutrient = item.getNutrient();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.isNew = item.getIsNew();
        this.isTesting = item.getIsTesting();
        this.orderLink = item.getOrderLink();
        this.category = item.getCategory();
    }
}

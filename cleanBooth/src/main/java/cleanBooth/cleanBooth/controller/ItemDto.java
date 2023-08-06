package cleanBooth.cleanBooth.controller;

import cleanBooth.cleanBooth.domain.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private String name;
    private String brandName;
    private int price;

    public ItemDto(Item item){
        this.name = item.getName();
        this.brandName = item.getBrandName();
        this.price = item.getPrice();
    }
}

package cleanBooth.cleanBooth.Mypage.Dto;

import cleanBooth.cleanBooth.domain.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MyItemDto {
    private Long itemId;
    private String name;
    private String brandName;
    private String image;

    public MyItemDto(Item item){
        this.itemId = item.getId();
        this.name = item.getName();
        this.brandName = item.getBrandName();
        this.image = item.getImage();
    }

}

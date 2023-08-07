package cleanBooth.cleanBooth.Item.Main.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ItemResponseDto {
    private String category;
    private List<ItemDto> itemList;

    public ItemResponseDto(String category, List<ItemDto> newDtos){
        this.category = category;
        itemList = newDtos;
    }
}

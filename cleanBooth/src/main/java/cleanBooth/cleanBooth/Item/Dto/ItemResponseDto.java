package cleanBooth.cleanBooth.Item.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ItemResponseDto {
    private String category; //카테고리
    private List<ItemDto> itemList;  //제품 리스트
    private Long totalCount; //총 제품 수

    public ItemResponseDto(String category, List<ItemDto> newDtos){
        this.category = category;
        itemList = newDtos;
        totalCount = newDtos.stream().count();
    }
}

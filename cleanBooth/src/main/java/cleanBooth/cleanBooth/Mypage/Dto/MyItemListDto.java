package cleanBooth.cleanBooth.Mypage.Dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MyItemListDto {
    private List<MyItemDto> item = new ArrayList<>();
    private int totalCount;

    public MyItemListDto(List<MyItemDto> item){
        this.item = item;
        totalCount = item.size();
    }
}

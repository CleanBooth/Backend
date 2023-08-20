package cleanBooth.cleanBooth.Item.Detail.Controller;

import cleanBooth.cleanBooth.Item.Detail.Dto.ItemDetailResponseDto;
import cleanBooth.cleanBooth.Item.Detail.Dto.ItemReviewDto;
import cleanBooth.cleanBooth.Item.Detail.Service.ItemDetailService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class ItemDetailController {

    private final ItemDetailService itemDetailService;

    @GetMapping("items/{itemId}/{orderBy}/")
    public ItemDetailResponseDto getItemDetail(
            @PathVariable("itemId")Long itemId,
            @PathVariable("orderBy")String orderBy) {
        return itemDetailService.findItemDetails(itemId, orderBy);
    }

//    @PostMapping("items/{itemId}/")
//    public ItemReviewDto postItemReview(
//            @PathVariable("itemId")Long itemId, @RequestBody HashMap<String, Object> map){
//
//    }
}


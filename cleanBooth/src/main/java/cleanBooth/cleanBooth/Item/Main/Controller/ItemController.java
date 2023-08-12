package cleanBooth.cleanBooth.Item.Main.Controller;

import cleanBooth.cleanBooth.Item.Main.Dto.ItemResponseDto;
import cleanBooth.cleanBooth.Item.Main.Service.ItemCategoryHomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemCategoryHomeService itemCategoryHomeService;

    @GetMapping("items/{category_name}/{orderBy}/")
    public ItemResponseDto getItemListHome(
            @PathVariable("category_name")String category_name,
            @PathVariable("orderBy")String orderBy) {
        return itemCategoryHomeService.findItemListHome(category_name, orderBy);
    }
}

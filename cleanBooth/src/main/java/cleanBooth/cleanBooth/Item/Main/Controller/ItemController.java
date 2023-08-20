package cleanBooth.cleanBooth.Item.Main.Controller;

import cleanBooth.cleanBooth.Item.Main.Dto.ItemResponseDto;
import cleanBooth.cleanBooth.Item.Main.Service.ItemCategoryHomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemCategoryHomeService itemCategoryHomeService;

    @GetMapping("items/category/{categoryName}/{orderBy}/")
    public ItemResponseDto getItemListCategoryHome(
            @PathVariable("categoryName")String categoryName,
            @PathVariable("orderBy")String orderBy) {
        return itemCategoryHomeService.findItemListHomeByCategory(categoryName, orderBy);
    }

    @GetMapping("items/nutrient/{nutrientAttribute}/{orderBy}/")
    public ItemResponseDto getItemListNutrientHome(
            @PathVariable("nutrientAttribute")String nutrientAttribute,
            @PathVariable("orderBy")String orderBy) {
        return itemCategoryHomeService.findItemListHomeByNutrient(nutrientAttribute, orderBy);
    }

}

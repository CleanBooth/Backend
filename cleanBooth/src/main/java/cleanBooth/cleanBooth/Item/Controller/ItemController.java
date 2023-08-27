package cleanBooth.cleanBooth.Item.Controller;

import cleanBooth.cleanBooth.Item.Dto.ItemDetailResponseDto;
import cleanBooth.cleanBooth.Item.Dto.PostReviewDto;
import cleanBooth.cleanBooth.Item.Dto.ItemResponseDto;
import cleanBooth.cleanBooth.Item.Service.ItemCategoryHomeService;
import cleanBooth.cleanBooth.Item.Service.ItemDetailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("items/")
public class ItemController {

    private final ItemCategoryHomeService itemCategoryHomeService;
    private final ItemDetailService itemDetailService;

    @Autowired
    private HttpServletRequest request;

    public String extractToken() {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 문자열 추출
            return accessToken;
        }

        return null;
    }

    @GetMapping("category/{categoryId}/{orderBy}/")
    public ItemResponseDto getItemListCategoryHome(
            @PathVariable("categoryId")Long categoryId,
            @PathVariable("orderBy")String orderBy) {
        String accessToken = extractToken();
        return itemCategoryHomeService.findItemListHomeByCategory(categoryId, orderBy, accessToken);
    }

    @GetMapping("nutrient/{nutrientId}/{orderBy}/")
    public ItemResponseDto getItemListNutrientHome(
            @PathVariable("nutrientId")Long nutrientId,
            @PathVariable("orderBy")String orderBy) {
        String accessToken = extractToken();
        return itemCategoryHomeService.findItemListHomeByNutrient(nutrientId, orderBy, accessToken);
    }

    @PutMapping("{itemId}/")
    public int saveWishItem(
            @PathVariable("itemId")Long itemId){
        String accessToken = extractToken();
        return itemCategoryHomeService.modifyWishItem(itemId, accessToken);
    }

    @GetMapping("{itemId}/{orderBy}/")
    public ItemDetailResponseDto getItemDetail(
            @PathVariable("itemId")Long itemId,
            @PathVariable("orderBy")String orderBy) {
        String accessToken = extractToken();
        return itemDetailService.findItemDetails(itemId, orderBy, accessToken);
    }

    @PostMapping("{itemId}/")
    public PostReviewDto postItemReview(
            @PathVariable("itemId")Long itemId,
            @RequestBody HashMap<String, Object> map){
        String accessToken = extractToken();
        return itemDetailService.postReviewDto(itemId, map, accessToken);
    }

    @PatchMapping("{itemId}/")
    public PostReviewDto patchItemReview(
            @PathVariable("itemId")Long itemId,
            @RequestBody HashMap<String, Object> map){
        String accessToken = extractToken();
        return itemDetailService.postReviewDto(itemId, map, accessToken);
    }

}

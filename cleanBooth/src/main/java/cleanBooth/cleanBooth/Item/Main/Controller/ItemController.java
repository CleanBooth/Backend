package cleanBooth.cleanBooth.Item.Main.Controller;

import cleanBooth.cleanBooth.Item.Main.Dto.ItemResponseDto;
import cleanBooth.cleanBooth.Item.Main.Service.ItemCategoryHomeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemCategoryHomeService itemCategoryHomeService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/extract-token")
    public String extractToken() {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 문자열 추출
            return "Extracted access token: " + accessToken;
        }

        return null;
    }

    @GetMapping("items/category/{categoryId}/{orderBy}/")
    public ItemResponseDto getItemListCategoryHome(
            @PathVariable("categoryId")Long categoryId,
            @PathVariable("orderBy")String orderBy) {
        String accessToken = extractToken();
        return itemCategoryHomeService.findItemListHomeByCategory(categoryId, orderBy, accessToken);
    }

    @GetMapping("items/nutrient/{nutrientId}/{orderBy}/")
    public ItemResponseDto getItemListNutrientHome(
            @PathVariable("nutrientId")Long nutrientId,
            @PathVariable("orderBy")String orderBy) {
        String accessToken = extractToken();
        return itemCategoryHomeService.findItemListHomeByNutrient(nutrientId, orderBy, accessToken);
    }

}

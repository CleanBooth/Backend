package cleanBooth.cleanBooth.Item.Detail.Controller;

import cleanBooth.cleanBooth.Item.Detail.Dto.ItemDetailResponseDto;
import cleanBooth.cleanBooth.Item.Detail.Dto.PostReviewDto;
import cleanBooth.cleanBooth.Item.Detail.Service.ItemDetailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class ItemDetailController {

    private final ItemDetailService itemDetailService;

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

    @GetMapping("items/{itemId}/{orderBy}/")
    public ItemDetailResponseDto getItemDetail(
            @PathVariable("itemId")Long itemId,
            @PathVariable("orderBy")String orderBy) {
        String accessToken = extractToken();
        return itemDetailService.findItemDetails(itemId, orderBy, accessToken);
    }

    @PostMapping("items/{itemId}/")
    public PostReviewDto postItemReview(
            @PathVariable("itemId")Long itemId,
            @RequestBody HashMap<String, Object> map){
        String accessToken = extractToken();
        return itemDetailService.postReviewDto(itemId, map, accessToken);
    }

    @PatchMapping("items/{itemId}/")
    public PostReviewDto patchItemReview(
            @PathVariable("itemId")Long itemId,
            @RequestBody HashMap<String, Object> map){
        String accessToken = extractToken();
        return itemDetailService.postReviewDto(itemId, map, accessToken);
    }
}


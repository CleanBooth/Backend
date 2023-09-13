package cleanBooth.cleanBooth.Mypage.Controller;

import cleanBooth.cleanBooth.Mypage.Dto.MyItemListDto;
import cleanBooth.cleanBooth.Mypage.Dto.MyRecipeListDto;
import cleanBooth.cleanBooth.Mypage.Dto.MyReviewPageDto;
import cleanBooth.cleanBooth.Mypage.Dto.MyTesterDto;
import cleanBooth.cleanBooth.Mypage.Service.MypageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;

    @Autowired
    private HttpServletRequest request;

    //액세스 토큰 추출을 위한 함수
    public String extractToken() {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 문자열 추출
            return accessToken;
        }
        return null;
    }


    @GetMapping("/wish-item/")
    public MyItemListDto myItemPage(){
        String accessToken = extractToken();
        return mypageService.findMyItemList(accessToken);
    }

    @GetMapping("/wish-recipe/")
    public MyRecipeListDto myRecipePage(){
        String accessToken = extractToken();
        return mypageService.findMyRecipeList(accessToken);
    }

    @GetMapping("/my-reviews/")
    public List<MyReviewPageDto> myReviewPage(){
        String accessToken = extractToken();
        return mypageService.findMyReviewPage(accessToken);
    }

    @GetMapping("/tester-history/")
    public List<MyTesterDto> myTesterPage(){
        String accessToken = extractToken();
        return mypageService.findMyTesterPage(accessToken);
    }
}

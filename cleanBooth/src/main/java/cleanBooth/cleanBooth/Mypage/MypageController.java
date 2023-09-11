package cleanBooth.cleanBooth.Mypage;

import cleanBooth.cleanBooth.Mypage.Dto.WishRecipeDto;
import cleanBooth.cleanBooth.Mypage.Response.WishRecipeResponse;
import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.repository.WishRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private WishRecipeRepository wishRecipeRepository;

    @GetMapping("/wish-recipe")
    public WishRecipeResponse GetWishRecipe(Long userId){
        List<Recipe> recipes = wishRecipeRepository.findLikedRecipesByUserId(userId);
        List<WishRecipeDto> wishRecipeDtoList = new ArrayList<>();

        for(Recipe recipe: recipes){
            WishRecipeDto wishRecipeDto = new WishRecipeDto(recipe);

            wishRecipeDtoList.add(wishRecipeDto);
        }

        WishRecipeResponse wishRecipeResponse = new WishRecipeResponse();
        wishRecipeResponse.setWishRecipeDtoList(wishRecipeDtoList);

        return wishRecipeResponse;
    }

    @DeleteMapping("/wish-recipe/{recipeId}")
    public String DeleteWishRecipe(@PathVariable Long recipeId){
        wishRecipeRepository.deleteWishRecipeById(recipeId);

        return "삭제 완료";
    }

}

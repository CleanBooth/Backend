package cleanBooth.cleanBooth.Mypage.Response;

import cleanBooth.cleanBooth.Mypage.Dto.WishRecipeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class WishRecipeResponse {
    private List<WishRecipeDto> wishRecipeDtoList;
}

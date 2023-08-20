package cleanBooth.cleanBooth.Item.Detail.Service;

import cleanBooth.cleanBooth.Item.Detail.Dto.ItemDetailResponseDto;
import cleanBooth.cleanBooth.Item.Detail.Dto.ItemRecipeDto;
import cleanBooth.cleanBooth.Item.Detail.Dto.ItemReviewDto;
import cleanBooth.cleanBooth.Item.Detail.Dto.PostReviewDto;
import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.Review;
import cleanBooth.cleanBooth.repository.ItemRepository;
import cleanBooth.cleanBooth.repository.RecipeRepository;
import cleanBooth.cleanBooth.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemDetailService {
    private final ItemRepository itemRepository;
    private final ReviewRepository reviewRepository;
    private final RecipeRepository recipeRepository;

    public ItemDetailResponseDto findItemDetails(Long itemId, String orderBy) { // 제품 상세페이지
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        Item item;
        if (optionalItem.isEmpty()) {  // 찾는 item이 없으면 오류 발생
            throw new IllegalStateException();
        } else {
            item = optionalItem.get();
        }
        List<Review> reviews = reviewRepository.findAllByItem_Id(itemId); // 아이템으로 리뷰 가져오기
        List<Review> sortedReviewList = new ArrayList<>();

        //정렬 처리
        if (orderBy.equals("latest")) {
            sortedReviewList = reviews.stream().sorted(Comparator.comparing(Review::getUploadDate)).toList();
            System.out.println("최신순으로 정렬");
        } else if (orderBy.equals("oldest")) {
            sortedReviewList = reviews.stream().sorted(Comparator.comparing(Review::getUploadDate).reversed()).toList();
            System.out.println("오래된순으로 정렬");
        } else {
            throw new IllegalStateException();
        }
        List<ItemReviewDto> itemReviewDtoList = sortedReviewList.stream()
                .map(review -> new ItemReviewDto(review)).toList();

        Random random = new Random();

        List<Recipe> recipes = new ArrayList<>();
        long maxNumber = recipeRepository.findAll().stream().count();
        for (int i = 0; i < 3; i++) {
            recipes.add(recipeRepository.findByID(random.nextLong(maxNumber)));
        }

        List<ItemRecipeDto> itemRecipeDtoList = recipes.stream()
                .map(recipe -> new ItemRecipeDto(recipe)).toList(); // 현재 등록한 레시피 중 랜덤하게 추천

        item.viewIncrease();  //조회수 상승

        return new ItemDetailResponseDto(item, itemReviewDtoList, itemRecipeDtoList);
    }

//    public PostReviewDto postReviewDto(Long itemId, HashMap<String, Object> map){
//        Optional<Item> optionalItem = itemRepository.findById(itemId);
//
//        Item item;
//        if (optionalItem.isEmpty()) {  // 찾는 item이 없으면 오류 발생
//            throw new IllegalStateException();
//        } else {
//            item = optionalItem.get();
//        }
//        Review review = new Review.
//
//
//    }
}

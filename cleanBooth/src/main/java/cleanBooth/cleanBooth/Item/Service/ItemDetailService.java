package cleanBooth.cleanBooth.Item.Service;

import cleanBooth.cleanBooth.Item.Dto.*;
import cleanBooth.cleanBooth.domain.*;
import cleanBooth.cleanBooth.repository.*;
import cleanBooth.cleanBooth.service.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemDetailService {
    private final ItemRepository itemRepository;
    private final ReviewRepository reviewRepository;
    private final RecipeRepository recipeRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final UserRepository userRepository;
    private final WishItemRepository wishItemRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    public ItemDetailResponseDto findItemDetails(Long itemId, String orderBy, String accessToken) { // 제품 상세페이지
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        Item item;
        if (optionalItem.isEmpty()) {  // 찾는 item이 없으면 오류 발생
            throw new IllegalStateException();
        } else {
            item = optionalItem.get();
        }
        ItemDetailDto itemDetailDto = new ItemDetailDto(item);
        List<Review> reviews = reviewRepository.findAllByItem_Id(itemId); // 아이템으로 리뷰 가져오기
        List<Review> sortedReviewList = new ArrayList<>();

        if (accessToken != null){
            Long memberId = authTokensGenerator.extractMemberId(accessToken);
            Optional<WishItem> optionalWishItem = wishItemRepository.findByItem_IdAndUser_Id(itemId, memberId);
            itemDetailDto.saveIsLiked(optionalWishItem);
        }

        //정렬 처리
        if (orderBy.equals("latest")) {
            sortedReviewList = reviews.stream().sorted(Comparator.comparing(Review::getUploadDate)).toList();
            System.out.println("최신순으로 정렬");
        } else if (orderBy.equals("oldest")) {
            sortedReviewList = reviews.stream().sorted(Comparator.comparing(Review::getUploadDate).reversed()).toList();
            System.out.println("오래된순으로 정렬");
        } else if (accessToken != null && orderBy.equals("myReview")){
            Long memberId = authTokensGenerator.extractMemberId(accessToken);
            Optional<User> user = userRepository.findById(memberId);
            reviews = reviewRepository.findAllByUserAndItem(user.get(), item);
            sortedReviewList = reviews.stream().sorted(Comparator.comparing(Review::getUploadDate)).toList();
            System.out.println("나의 리뷰");
        } else {
            throw new RuntimeException("token or orderBy 오류");
        }
        List<ItemReviewDto> itemReviewDtoList = sortedReviewList.stream()
                .map(review -> new ItemReviewDto(review)).toList();

        Random random = new Random();

        List<Recipe> recipes = new ArrayList<>();
        long maxNumber = recipeRepository.findAll().stream().count();
        for (int i = 0; i < 3; i++) {
            recipes.add(recipeRepository.findByID(random.nextLong(maxNumber)+1));
        }

        List<ItemRecipeDto> itemRecipeDtoList = recipes.stream()
                .map(recipe -> new ItemRecipeDto(recipe)).toList(); // 현재 등록한 레시피 중 랜덤하게 추천

        item.viewIncrease();  //조회수 상승

        return new ItemDetailResponseDto(itemDetailDto, itemReviewDtoList, itemRecipeDtoList);
    }

    public PostReviewDto postReviewDto(Long itemId, HashMap<String, Object> map, String accessToken){
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        Long memberId = authTokensGenerator.extractMemberId(accessToken);
        PostReviewDto postReviewDto = new PostReviewDto((String) map.get("goodDescription"),
                (String) map.get("badDescription"), (Double) map.get("score"), (List<String>) map.get("image"));


        Optional<User> optionalUser = userRepository.findById(memberId);
        User user;
        if (optionalItem.isEmpty()) {
            throw new IllegalStateException();
        }
        else {
            user = optionalUser.get();
        }

        Item item;
        if (optionalItem.isEmpty()) {  // 찾는 item 이 없으면 오류 발생
            throw new IllegalStateException();
        } else {
            item = optionalItem.get();
        }
        Review review = new Review(item, user, postReviewDto.getGoodDescription(), postReviewDto.getBadDescription(),
                postReviewDto.getScore());
        reviewRepository.save(review);
        item.updateReviewCount();

        for (int i = 0; i < postReviewDto.getPhotos().size(); i++) {
            ReviewPhoto reviewPhoto = new ReviewPhoto(review, postReviewDto.getPhotos().get(i));
            reviewPhotoRepository.save(reviewPhoto);
        }

        return postReviewDto;
    }
}

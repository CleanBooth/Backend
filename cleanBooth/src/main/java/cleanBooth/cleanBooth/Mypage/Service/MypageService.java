package cleanBooth.cleanBooth.Mypage.Service;

import cleanBooth.cleanBooth.Mypage.Dto.*;
import cleanBooth.cleanBooth.domain.*;
import cleanBooth.cleanBooth.repository.*;
import cleanBooth.cleanBooth.service.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MypageService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final WishItemRepository wishItemRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final WishRecipeRepository wishRecipeRepository;
    private final ReviewRepository reviewRepository;
    private final TesterHistoryRepository testerHistoryRepository;

    // 찜한 상품 페이지
    public MyItemListDto findMyItemList(String accessToken){

        if (accessToken == null){ //유저가 로그인 되어있지 않다면
            throw new IllegalStateException();
        }
        Long memberId = authTokensGenerator.extractMemberId(accessToken);

        Optional<User> optionalUser = userRepository.findById(memberId);

        if (optionalUser.isEmpty()){ //유저가 유효하지 않으면 에러 리턴
            throw new IllegalStateException();
        }

        List<WishItem> wishItemList = wishItemRepository.findByUserId(memberId);

        List<Item> items = wishItemList.stream().map(WishItem::getItem).toList();

        List<MyItemDto> myItemDtos = items.stream().map(item -> new MyItemDto(item)).toList();

        return new MyItemListDto(myItemDtos);
    }

    public MyRecipeListDto findMyRecipeList(String accessToken){

        if (accessToken == null){ //유저가 로그인 되어있지 않다면
            throw new IllegalStateException();
        }
        Long memberId = authTokensGenerator.extractMemberId(accessToken);

        Optional<User> optionalUser = userRepository.findById(memberId);

        if (optionalUser.isEmpty()){ //유저가 유효하지 않으면 에러 리턴
            throw new IllegalStateException();
        }
        List<WishRecipe> wishRecipeList = wishRecipeRepository.findAllByUserId(memberId);

        List<Recipe> recipes = wishRecipeList.stream().map(WishRecipe::getRecipe).toList();

        List<MyRecipeDto> myRecipeDtos = recipes.stream().map(recipe -> new MyRecipeDto(recipe)).toList();

        return new MyRecipeListDto(myRecipeDtos);
    }

    public List<MyReviewPageDto> findMyReviewPage(String accessToken){
        if (accessToken == null){ //유저가 로그인 되어있지 않다면
            throw new IllegalStateException();
        }
        Long memberId = authTokensGenerator.extractMemberId(accessToken);

        Optional<User> optionalUser = userRepository.findById(memberId);

        if (optionalUser.isEmpty()){ //유저가 유효하지 않으면 에러 리턴
            throw new IllegalStateException();
        }
        List<Review> reviews = reviewRepository.findAllByUser(optionalUser.get());

        List<MyReviewPageDto> myReviews = reviews.stream().map(review -> new MyReviewPageDto(review)).toList();

        return myReviews;
    }

    public List<MyTesterDto> findMyTesterPage(String accessToken) {

        if (accessToken == null) { //유저가 로그인 되어있지 않다면
            throw new IllegalStateException();
        }
        Long memberId = authTokensGenerator.extractMemberId(accessToken);

        Optional<User> optionalUser = userRepository.findById(memberId);

        if (optionalUser.isEmpty()) { //유저가 유효하지 않으면 에러 리턴
            throw new IllegalStateException();
        }

        List<TesterHistory> testerHistories = testerHistoryRepository.findAllByUser(optionalUser.get());

        // MyTesterDto 리스트 생성
        List<MyTesterDto> myTesterDtos = new ArrayList<>();

        for (TesterHistory history : testerHistories) {
            MyTesterDto myTesterDto = new MyTesterDto();
            Tester tester = history.getTester();
            Item item = tester.getItem();

            myTesterDto.setTesterId(tester.getId());
            myTesterDto.setIsWin(history.isWin());
            myTesterDto.setItemName(item.getName());
            myTesterDto.setItemImage(item.getImage());
            myTesterDto.setEndDate(tester.getEndDate());
            myTesterDto.setIsTesting(tester.isTesting());
            myTesterDto.setIsReviewed(history.isReviewed());

            myTesterDtos.add(myTesterDto);
        }
        return myTesterDtos;
    }
}

package cleanBooth.cleanBooth.Item.Service;

import cleanBooth.cleanBooth.Item.Dto.ItemDto;
import cleanBooth.cleanBooth.Item.Dto.ItemResponseDto;
import cleanBooth.cleanBooth.domain.Category;
import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.domain.User;
import cleanBooth.cleanBooth.domain.WishItem;
import cleanBooth.cleanBooth.repository.CategoryRepository;
import cleanBooth.cleanBooth.repository.ItemRepository;
import cleanBooth.cleanBooth.repository.UserRepository;
import cleanBooth.cleanBooth.repository.WishItemRepository;
import cleanBooth.cleanBooth.service.AuthTokensGenerator;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemCategoryHomeService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final UserRepository userRepository;
    private final WishItemRepository wishItemRepository;

    public ItemResponseDto findItemListHomeByCategory (Long categoryId, String orderBy, String accessToken){

        List<Item> categoryItemList = new ArrayList<>();
        List<Item> sortedItemList = new ArrayList<>();

        //카테고리로 필터링
        Optional<Category> myCategory = categoryRepository.findById(categoryId);

        Category category;
        if (myCategory.isEmpty()){ //카테고리가 유효한 카테고리가 아닐때
            throw new IllegalStateException();
        }
        else {
            category = myCategory.get();
            categoryItemList = itemRepository.findAllByCategory(category);
            System.out.println("카테고리로 item 찾는중");
        }

        //정렬처리
        if (orderBy.equals("recommend")){ //추천순
            sortedItemList = categoryItemList.stream()
                    .sorted(Comparator.comparing(Item::getAvgRating))
                    .toList();
            System.out.println("추천순으로 정렬");
        } else if (orderBy.equals("popular")) { //인기순 (조회순)
            sortedItemList = categoryItemList.stream()
                    .sorted(Comparator.comparing(Item::getIsViewed))
                    .toList();
            System.out.println("인기순으로 정렬");
        }
        else if (orderBy.equals("manyReview")){ //리뷰 많은 순
            sortedItemList = categoryItemList.stream()
                    .sorted(Comparator.comparing(Item::getReviewCount))
                    .toList();
            System.out.println("리뷰 많은 순으로 정령");
        }

        List<ItemDto> itemDtoList = sortedItemList.stream()
                .map(item -> new ItemDto(item))
                .toList();

        if (accessToken != null){
            Long memberId = authTokensGenerator.extractMemberId(accessToken);
            for(ItemDto itemDto: itemDtoList){
                itemDto.saveIsLiked(wishItemRepository.findByItem_IdAndUser_Id(itemDto.getItemId(), memberId));
            }
        }

        System.out.println("response 데이터로 변환중");
        return new ItemResponseDto(category.getName(), itemDtoList);
    }

    public ItemResponseDto findItemListHomeByNutrient (Long nutrientId, String orderBy, String accessToken){

        List<Item> ingredientItemList = new ArrayList<>();
        List<Item> sortedItemList = new ArrayList<>();

        Optional<Category> optionalCategory = categoryRepository.findById(nutrientId);  //영양성분으로 필터링

        Category nutrient;
        if (optionalCategory.isEmpty()){ //영양성분이 유효한 카테고리가 아닐때
            throw new IllegalStateException();
        }
        else {
            nutrient = optionalCategory.get();
            ingredientItemList = itemRepository.findAllByCategory(nutrient);
            System.out.println("영양성분로 item 찾는중");
        }

        //정렬처리
        if (orderBy.equals("recommend")){ //추천순
            sortedItemList = ingredientItemList.stream()
                    .sorted(Comparator.comparing(Item::getAvgRating))
                    .toList();
            System.out.println("추천순으로 정렬");
        } else if (orderBy.equals("popular")) { //인기순 (조회순)
            sortedItemList = ingredientItemList.stream()
                    .sorted(Comparator.comparing(Item::getIsViewed))
                    .toList();
            System.out.println("인기순으로 정렬");
        }
        else if (orderBy.equals("manyReview")) { //리뷰 많은 순
            sortedItemList = ingredientItemList.stream()
                    .sorted(Comparator.comparing(Item::getReviewCount))
                    .toList();
            System.out.println("리뷰 많은 순으로 정령");
        }

        List<ItemDto> itemDtoList = sortedItemList.stream()
                .map(item -> new ItemDto(item))
                .toList();

        if (accessToken != null){
            Long memberId = authTokensGenerator.extractMemberId(accessToken);
            for(ItemDto itemDto: itemDtoList){
                itemDto.saveIsLiked(wishItemRepository.findByItem_IdAndUser_Id(itemDto.getItemId(), memberId));
            }
        }

        System.out.println("response 데이터로 변환중");
        return new ItemResponseDto(nutrient.getName(), itemDtoList);
    }

    public int modifyWishItem(Long itemId, String accessToken){
        if (accessToken == null){
            return HttpServletResponseWrapper.SC_NOT_FOUND;
        }
        Long memberId = authTokensGenerator.extractMemberId(accessToken);
        Optional<WishItem> optionalWishItem = wishItemRepository.findByItem_IdAndUser_Id(itemId, memberId);
        if (optionalWishItem.isEmpty()){
            Item item = itemRepository.findById(itemId).get();
            User user = userRepository.findById(memberId).get();
            WishItem wishItem = new WishItem(item, user);
            wishItemRepository.save(wishItem);
            return HttpServletResponseWrapper.SC_CREATED;
        }
        else {
            WishItem wishItem = optionalWishItem.get();
            wishItemRepository.delete(wishItem);
            return HttpServletResponseWrapper.SC_ACCEPTED;
        }
    }

}

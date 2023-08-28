package cleanBooth.cleanBooth.Item.Main.Service;

import cleanBooth.cleanBooth.Item.Main.Dto.ItemDto;
import cleanBooth.cleanBooth.Item.Main.Dto.ItemResponseDto;
import cleanBooth.cleanBooth.domain.Category;
import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.repository.CategoryRepository;
import cleanBooth.cleanBooth.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemCategoryHomeService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemResponseDto findItemListHomeByCategory (Long categoryId, String orderBy){

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


        System.out.println("response 데이터로 변환중");
        return new ItemResponseDto(category.getName(), itemDtoList);
    }

    public ItemResponseDto findItemListHomeByNutrient (Long nutrientId, String orderBy){

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


        System.out.println("response 데이터로 변환중");
        return new ItemResponseDto(nutrient.getName(), itemDtoList);
    }

}

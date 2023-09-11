package cleanBooth.cleanBooth.Mypage.Dto;

import cleanBooth.cleanBooth.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MyReviewPageDto {
    private MyItemDto item;
    private MyReviewDto review;

    public MyReviewPageDto(Review review){
        this.item = new MyItemDto(review.getItem());
        this.review = new MyReviewDto(review);
    }
}

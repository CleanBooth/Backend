package cleanBooth.cleanBooth.Mypage.Dto;

import cleanBooth.cleanBooth.domain.Review;
import cleanBooth.cleanBooth.domain.ReviewPhoto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MyReviewDto {
    private String goodDescription;
    private String badDescription;
    private String updatedAt;
    private List<String> images;

    public MyReviewDto(Review review){
        this.goodDescription = review.getGoodDescription();
        this.badDescription = review.getBadDescription();
        this.updatedAt = review.getUploadDate().toString();
        this.images = review.getPhotos().stream().map(ReviewPhoto::getImage).toList();
    }
}

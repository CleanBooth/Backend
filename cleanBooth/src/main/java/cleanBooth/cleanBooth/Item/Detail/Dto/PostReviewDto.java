package cleanBooth.cleanBooth.Item.Detail.Dto;

import cleanBooth.cleanBooth.domain.Review;
import lombok.Builder;

public class PostReviewDto {
    private String goodDescription;
    private String badDescription;
    private Float score;

    @Builder
    public PostReviewDto(String goodDescription, String badDescription, Float score){
        this.goodDescription = goodDescription;
        this.badDescription = badDescription;
        this.score = score;
    }
}

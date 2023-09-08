package cleanBooth.cleanBooth.Item.Dto;

import cleanBooth.cleanBooth.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostReviewDto {
    private String goodDescription;
    private String badDescription;
    private Double score;
    private List<String> photos;

    @Builder
    public PostReviewDto(String goodDescription, String badDescription, Double score, List<String> image){
        this.goodDescription = goodDescription;
        this.badDescription = badDescription;
        this.score = score;
        this.photos = image;
    }
}

package cleanBooth.cleanBooth.Item.Dto;

import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.domain.Review;
import cleanBooth.cleanBooth.domain.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ItemReviewDto {

    private String userName;
    private String userGeneration;
    private String goodDescription;
    private String badDescription;
    private Double score;
    private String uploadDate;

    public ItemReviewDto(Review review){
        this.userName = review.getUser().getName();
        this.userGeneration = review.getUser().getAge();
        this.goodDescription = review.getGoodDescription();
        this.badDescription = review.getBadDescription();
        this.score = review.getScore();
        this.uploadDate = review.getUploadDate().toString();
    }
}

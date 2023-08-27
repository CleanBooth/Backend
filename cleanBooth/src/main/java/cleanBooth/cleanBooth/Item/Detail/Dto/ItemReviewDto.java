package cleanBooth.cleanBooth.Item.Detail.Dto;

import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.domain.Review;
import cleanBooth.cleanBooth.domain.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class ItemReviewDto {

    private String userName;
    private String userGeneration;
    private String goodDescription;
    private String badDescription;
    private Float score;
    private String uploadDate;

    public ItemReviewDto(Review review){
        this.userName = review.getUser().getName();
        this.userGeneration = review.getUser().getBirthday();  // 연령대 추가되면 변경해야 함
        this.goodDescription = review.getGoodDescription();
        this.badDescription = review.getBadDescription();
        this.score = review.getScore();
        this.uploadDate = review.getUploadDate().toString();
    }
}

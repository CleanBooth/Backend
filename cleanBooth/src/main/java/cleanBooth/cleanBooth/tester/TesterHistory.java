package cleanBooth.cleanBooth.tester;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TesterHistory {

    @Id
    @GeneratedValue
    private Long id;
    private Long testerId;
    private Long userId;

    private Long reviewId;

    private String name;

    private String phoneNum;

    private String address;

    private String message;

    private boolean isWin;

    private String option;

    private boolean isReviewed;

    public TesterHistory() {
    }

    public TesterHistory(Long testerId, Long userId, Long reviewId, String name, String phoneNum, String address, String message, boolean isWin, String option, boolean isReviewed) {
        this.testerId = testerId;
        this.userId = userId;
        this.reviewId = reviewId;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.message = message;
        this.isWin = isWin;
        this.option = option;
        this.isReviewed = isReviewed;
    }
}

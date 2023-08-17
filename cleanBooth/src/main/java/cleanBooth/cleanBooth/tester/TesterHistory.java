package cleanBooth.cleanBooth.tester;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TesterHistory {
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

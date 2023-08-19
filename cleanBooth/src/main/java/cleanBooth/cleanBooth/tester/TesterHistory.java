package cleanBooth.cleanBooth.tester;

import cleanBooth.cleanBooth.domain.Review;
import cleanBooth.cleanBooth.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TesterHistory {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tester tester;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    private String name;

    private String phoneNum;

    private String address;

    private String message;

    private boolean isWin;

    private String option;

    private boolean isReviewed;

    public TesterHistory() {
    }

    public TesterHistory(Tester tester, User user, Review review, String name, String phoneNum, String address, String message, boolean isWin, String option, boolean isReviewed) {
        this.tester = tester;
        this.user = user;
        this.review = review;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.message = message;
        this.isWin = false;
        this.option = option;
        this.isReviewed = false;
    }

    public void doWin() {
        this.isWin = true;
    }

    public void Reviewed() {
        this.isReviewed = true;
    }
}

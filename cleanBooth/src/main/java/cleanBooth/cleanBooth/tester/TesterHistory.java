package cleanBooth.cleanBooth.tester;

import cleanBooth.cleanBooth.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

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

    private String name;

    private String phoneNum;

    private String address;

    private String message;

    private boolean isWin;

    private String options;

    private boolean isReviewed;

    public TesterHistory() {
    }

    public TesterHistory(Tester tester, User user, String name, String phoneNum, String address, String message, boolean isWin, String options, boolean isReviewed) {
        this.tester = tester;
        this.user = user;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.message = message;
        this.isWin = false;
        this.options = options;
        this.isReviewed = false;
    }

    public void changeWin() {
        this.isWin = !isWin;
    }

    public void changeReviewed() {
        this.isReviewed = !isReviewed;
    }

}

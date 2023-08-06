package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id @GeneratedValue
    @Column(name = "REVIEW_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String goodDescription;
    private String badDescription;
    private Float score;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    @Builder
    public Review(Long id, Item item, User user, String goodDescription, String badDescription, Float score, Date uploadDate) {
        this.id = id;
        this.item = item;
        this.user = user;
        this.goodDescription = goodDescription;
        this.badDescription = badDescription;
        this.score = score;
        this.uploadDate = uploadDate;
    }
}

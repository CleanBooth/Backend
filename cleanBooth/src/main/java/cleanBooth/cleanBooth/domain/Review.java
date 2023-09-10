package cleanBooth.cleanBooth.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
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
    private Double score;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @CreatedDate
    private Date uploadDate;

    @OneToMany(mappedBy = "review")
    @Nullable
    private List<ReviewPhoto> photos;

    @Builder
    public Review(Item item, User user, String goodDescription, String badDescription, Double score) {
        this.item = item;
        this.user = user;
        this.goodDescription = goodDescription;
        this.badDescription = badDescription;
        this.score = score;
    }
}

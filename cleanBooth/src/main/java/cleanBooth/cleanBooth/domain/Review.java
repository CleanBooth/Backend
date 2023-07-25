package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
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
    private Date uploadDate;

}

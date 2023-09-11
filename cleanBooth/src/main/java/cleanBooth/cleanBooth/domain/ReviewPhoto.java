package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ReviewPhoto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_PHOTO_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    private String image;

    @Builder
    public ReviewPhoto(Review review, String image){
        this.review = review;
        this.image = image;
    }
}

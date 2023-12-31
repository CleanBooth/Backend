package cleanBooth.cleanBooth.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private String brandName;

    private Nutrient nutrientInfo;
    private String allergyInfo;
    private String ingredientInfo;

    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    private Integer price;
    private String image;

    @Enumerated(EnumType.STRING)
    private TestingStatus isTesting;

    private String orderLink;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Long isViewed = 0L;
    private Long reviewCount = 0L;

    @OneToMany(mappedBy = "item")
    @Nullable
    private List<WishItem> likedUser;

    @Builder
    public Item(Long id, String name, String brandName, String description, String nutrient, Integer price, String image,
                TestingStatus isTesting, Category category, Float testerRate, String orderLink, String allergyInfo) {
        this.id = id;
        this.name = name;
        this.brandName = brandName;
        this.price = price;
        this.image = image;
        this.isTesting = isTesting;
        this.category = category;
        this.orderLink = orderLink;
        this.allergyInfo = allergyInfo;
    }


    public Double getAvgRating(){
        List<Double> scores = reviews.stream().map(Review::getScore).collect(Collectors.toList());
        Double totalScore = (double) 0;
        for (Double score: scores){
            totalScore += score;
        }
        if (reviewCount == 0){
            return (double) 0;
        }
        else return totalScore/reviewCount;
    }

    public void viewIncrease(){
        this.isViewed++;
    }

    public void updateReviewCount(){
        this.reviewCount = this.reviews.stream().count();
    }

/*    // public 생성자 임시로 생성
    public Item() {
        // 기본 생성자 내용
    }
    // setter 메서드 임시로 생성
    public void setName(String name) {
        this.name = name;
    }*/

}

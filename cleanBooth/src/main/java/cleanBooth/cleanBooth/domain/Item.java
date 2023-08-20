package cleanBooth.cleanBooth.domain;

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
    private NewStatus isNew;

    @Enumerated(EnumType.STRING)
    private TestingStatus isTesting;

    private String orderLink;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Long isViewed = 0L;
    private Long reviewCount = this.reviews.stream().count();


    @Builder
    public Item(Long id, String name, String brandName, String description, String nutrient, Integer price, String image,
                NewStatus isNew, TestingStatus isTesting, Category category, Float testerRate, String orderLink, String allergyInfo) {
        this.id = id;
        this.name = name;
        this.brandName = brandName;
        this.price = price;
        this.image = image;
        this.isNew = isNew;
        this.isTesting = isTesting;
        this.category = category;
        this.orderLink = orderLink;
        this.allergyInfo = allergyInfo;
    }


    public Float getAvgRating(){
        List<Float> scores = reviews.stream().map(Review::getScore).collect(Collectors.toList());
        float totalScore = 0;
        for (float score: scores){
            totalScore += score;
        }
        return totalScore/reviewCount;
    }

    public void viewIncrease(){
        this.isViewed++;
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

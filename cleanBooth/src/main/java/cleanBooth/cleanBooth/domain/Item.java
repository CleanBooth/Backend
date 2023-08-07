package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private String brandName;
    private TextField description;
    private Nutrient nutrient;

    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    private Integer price;
    private String image;

    @Enumerated(EnumType.STRING)
    private NewStatus isNew;

    @Enumerated(EnumType.STRING)
    private TestingStatus isTesting;

    private Float testerRate;
    private String orderLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    private Long isViewed = 0L;
    private int reviewCount = this.reviews.size();


    @Builder
    public Item(Long id, String name, String brandName, TextField description, Nutrient nutrient, Integer price, String image, NewStatus isNew, TestingStatus isTesting, Category category, Float testerRate, String orderLink) {
        this.id = id;
        this.name = name;
        this.brandName = brandName;
        this.description = description;
        this.nutrient = nutrient;
        this.price = price;
        this.image = image;
        this.isNew = isNew;
        this.isTesting = isTesting;
        this.category = category;
        this.testerRate = testerRate;
        this.orderLink = orderLink;

    }

}

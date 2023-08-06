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

    @ManyToOne(fetch = FetchType.LAZY)
    private ItemCategory itemCategory;

    private Float testerRate;
    private String orderLink;

    @Builder
    public Item(Long id, String name, String brandName, TextField description, Nutrient nutrient, List<Review> reviews, Integer price, String image, NewStatus isNew, TestingStatus isTesting, ItemCategory itemCategory, Float testerRate, String orderLink) {
        this.id = id;
        this.name = name;
        this.brandName = brandName;
        this.description = description;
        this.nutrient = nutrient;
        this.reviews = reviews;
        this.price = price;
        this.image = image;
        this.isNew = isNew;
        this.isTesting = isTesting;
        this.itemCategory = itemCategory;
        this.testerRate = testerRate;
        this.orderLink = orderLink;
    }

}

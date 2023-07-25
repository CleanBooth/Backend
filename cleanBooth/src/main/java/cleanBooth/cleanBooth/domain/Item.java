package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private TextField description;
    private Nutrient nutrient;

    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    private int price;
    private String image;

    @Enumerated(EnumType.STRING)
    private Enum<NewStatus> isNew;

    @Enumerated(EnumType.STRING)
    private Enum<TestingStatus> isTesting;

    @ManyToOne(fetch = FetchType.LAZY)
    private List<ItemCategory> itemCategories = new ArrayList<>();

    private Float testerRate;
    private String orderLink;

}

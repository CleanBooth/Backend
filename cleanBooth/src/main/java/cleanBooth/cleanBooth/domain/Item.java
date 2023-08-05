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
    private String brandName;
    private TextField description;
    private Nutrient nutrient;

    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    private int price;
    private String image;

    @Enumerated(EnumType.STRING)
    private NewStatus isNew;

    @Enumerated(EnumType.STRING)
    private TestingStatus isTesting;

    @ManyToOne(fetch = FetchType.LAZY)
    private ItemCategory itemCategory;

    private Float testerRate;
    private String orderLink;

}

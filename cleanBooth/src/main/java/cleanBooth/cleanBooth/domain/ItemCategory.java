package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ItemCategory {

    @Id @GeneratedValue
    @Column(name = "ITEM_CATEGORY_ID")
    private Long id;

    //@OneToMany(mappedBy = "itemCategory")
    //private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "itemCategory")
    private List<Category> categories = new ArrayList<>();

}

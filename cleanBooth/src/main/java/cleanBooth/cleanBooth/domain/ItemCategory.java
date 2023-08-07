package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ItemCategory {

    @Id @GeneratedValue
    @Column(name = "ITEM_CATEGORY_ID")
    private Long id;

    @OneToMany(mappedBy = "itemCategory")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "itemCategory")
    private List<Category> categories = new ArrayList<>();

}

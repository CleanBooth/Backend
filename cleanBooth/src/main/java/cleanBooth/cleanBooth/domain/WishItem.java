package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class WishItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WISH_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public WishItem(Item item, User user){
        this.item = item;
        this.user = user;
    }
}

package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class WishRecipe {
    @Id
    @GeneratedValue
    @Column(name = "WISH_RECIPE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public WishRecipe(Recipe recipe, User user){
        this.recipe = recipe;
        this.user = user;
    }
}

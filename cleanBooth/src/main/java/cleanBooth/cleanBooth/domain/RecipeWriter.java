package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class RecipeWriter {

    @Id
    @GeneratedValue
    @Column(name = "WRITER_ID")
    private Long id;

    private String name;
    private String link;

    @OneToMany(mappedBy = "recipeWriter")
    private List<Recipe> recipeList = new ArrayList<>();
}

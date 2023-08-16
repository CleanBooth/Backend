package cleanBooth.cleanBooth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Recipe {

    @Id @GeneratedValue
    @Column(name = "RECIPE_ID")
    private Long id;

    private String name;
    private String styles;

    private String link;
    private String videoTitle;
    private Boolean isLike;

    @Enumerated(EnumType.STRING)
    private Site site;

    @ManyToOne(fetch = FetchType.EAGER)
    private RecipeWriter recipeWriter;

}

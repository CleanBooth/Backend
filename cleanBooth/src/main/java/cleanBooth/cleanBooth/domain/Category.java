package cleanBooth.cleanBooth.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;
    private String name;

    @Builder
    public Category(String name, Category parent){
        this.name = name;
    }


}

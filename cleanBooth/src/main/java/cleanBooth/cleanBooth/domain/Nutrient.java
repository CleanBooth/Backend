package cleanBooth.cleanBooth.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Nutrient {
    private String salt;
    private String carbohydrate;
    private String sweet;
    private String fat;
    private String transFat;
    private String saturatedFat;
    private String cholesterol;
    private String protein;

}

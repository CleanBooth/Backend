package cleanBooth.cleanBooth.domain;

import jakarta.persistence.Embeddable;

@Embeddable
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

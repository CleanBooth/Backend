package cleanBooth.cleanBooth.domain;

import jakarta.persistence.Embeddable;

import static java.lang.Boolean.*;

@Embeddable
public class Nutrient {

    private Boolean veganProtein = FALSE;
    private Boolean Almond = FALSE;
    private Boolean oats = FALSE;
    private Boolean coconut = FALSE;
    private Boolean chiaSeeds = FALSE;
    private Boolean lentils = FALSE;
    private Boolean chickpeas = FALSE;
    private Boolean quinoa = FALSE;

    private Boolean highProteinLowCarbohydrate = FALSE;
    private Boolean lowCalorie = FALSE;
    private Boolean lowSalt = FALSE;
    private Boolean freeSugar = FALSE;
    private Boolean naturalSweet = FALSE;
    private Boolean glutenFree = FALSE;
    private Boolean exerciseSupplement = FALSE;

}

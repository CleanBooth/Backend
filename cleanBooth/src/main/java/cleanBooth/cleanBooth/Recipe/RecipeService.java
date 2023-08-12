package cleanBooth.cleanBooth.Recipe;


import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.Site;
import cleanBooth.cleanBooth.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public void write(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public List<Recipe> RecipeList(){
        return recipeRepository.findAll();
    }

    public Recipe Recipe(@PathVariable long recipeId, Model model){
        return recipeRepository.findByID(recipeId);
    }

    public List<Recipe> findRecipeByStyle(@PathVariable String style){
        List<Recipe> resultList = recipeRepository.findByStyle(style);

        return resultList;
    }

    public List<Recipe> findRecipeBySite(@PathVariable Site site){
        List<Recipe> resultList = recipeRepository.findBySite(site);

        return resultList;
    }


}
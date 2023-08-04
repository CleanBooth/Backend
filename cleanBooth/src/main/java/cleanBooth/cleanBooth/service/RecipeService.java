package cleanBooth.cleanBooth.service;


import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping("/recipe")
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/save")
    public void write(Recipe recipe){
        recipeRepository.save(recipe);
    }

    @GetMapping("/list")
    public List<Recipe> RecipeList(){
        return recipeRepository.findAll();
    }

    @GetMapping("/{recipeId}")
    public String Recipe(@PathVariable long recipeId, Model model){
        Recipe recipe = recipeRepository.findByID(recipeId);
        model.addAttribute("recipe", recipe);

        return "basic/recipe"; //html
    }

    @GetMapping("/{style}")
    public List<Recipe> findRecipeByStyle(@PathVariable String style){
        List<Recipe> resultList = recipeRepository.findByStyle(style);

        return resultList;
    }

    @GetMapping("/{site}")
    public List<Recipe> findRecipeBySite(@PathVariable String site){
        List<Recipe> resultList = recipeRepository.findBySite(site);

        return resultList;
    }


}
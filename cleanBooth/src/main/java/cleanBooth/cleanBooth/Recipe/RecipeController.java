package cleanBooth.cleanBooth.Recipe;

import cleanBooth.cleanBooth.Recipe.Response.RecipeListResponse;
import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.Site;
import cleanBooth.cleanBooth.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/recipe")
    public ResponseEntity<Map<String, List<String>>> getWriters() {
        List<String> uWriters = recipeRepository.findBySite(Site.YouTube);
        List<String> bWriters = recipeRepository.findBySite(Site.Blog);

        Map<String, List<String>> response = new HashMap<>();
        response.put("uWriter", uWriters);
        response.put("bWriter", bWriters);

        return ResponseEntity.ok(response);
    }

    //@GetMapping("/{writer_name}")

    @GetMapping("/recipes")
    public RecipeListResponse stylefilter(@RequestBody List<String> styles){
        List<Recipe> recipes = recipeRepository.findByStyle(styles);
        int totalCount = recipes.size();

        RecipeListResponse response = new RecipeListResponse();
        response.setRecipes(recipes);
        response.setTotalCount(totalCount);

        return response;
    }


    @GetMapping("/recipe/save")
    public void write(Recipe recipe){
        recipeRepository.save(recipe);
    }

    @GetMapping("/recipe/list")
    public List<Recipe> RecipeList(){
        return recipeRepository.findAll();
    }

    @GetMapping("/recipe/{recipeId}")
    public String Recipe(@PathVariable long recipeId, Model model){
        Recipe recipe = recipeRepository.findByID(recipeId);
        model.addAttribute("recipe", recipe);

        return "basic/recipe"; //html
    }

    @GetMapping("/recipe/style/{style}")
    public List<Recipe> findRecipeByStyle(@PathVariable String style){
        List<Recipe> resultList = recipeRepository.findByStyle(style);

        return resultList;
    }

}

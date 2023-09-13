package cleanBooth.cleanBooth.Recipe;

import cleanBooth.cleanBooth.Recipe.Dto.RecipeDto;
import cleanBooth.cleanBooth.Recipe.Dto.RecipeFilterDto;
import cleanBooth.cleanBooth.Recipe.Dto.RecipeIdDto;
import cleanBooth.cleanBooth.Recipe.Dto.RecipeWriterDto;
import cleanBooth.cleanBooth.Recipe.Response.RecipeIdResponse;
import cleanBooth.cleanBooth.Recipe.Response.RecipeListResponse;
import cleanBooth.cleanBooth.Recipe.Response.RecipeWriterResponse;
import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.RecipeWriter;
import cleanBooth.cleanBooth.domain.Site;
import cleanBooth.cleanBooth.repository.RecipeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private HttpServletRequest request;

    public String extractToken() {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 문자열 추출
            return accessToken;
        }

        return null;
    }

    @GetMapping("/recipe")
    public ResponseEntity<Map<String, List<String>>> getWriters() {
        List<String> uWriters = recipeRepository.findBySite(Site.YouTube);
        List<String> bWriters = recipeRepository.findBySite(Site.Blog);

        Map<String, List<String>> response = new HashMap<>();
        response.put("uWriter", uWriters);
        response.put("bWriter", bWriters);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/recipes")
    public RecipeListResponse stylefilter(@RequestBody List<String> styles){
        List<Recipe> recipes = recipeRepository.findByStyle(styles);
        List<RecipeFilterDto> recipeFilterDtos = new ArrayList<>();

        for (Recipe recipe: recipes) {
            RecipeFilterDto dto = new RecipeFilterDto(recipe);

            recipeFilterDtos.add(dto);
        }
        int totalCount = recipes.size();

        RecipeListResponse response = new RecipeListResponse();
        response.setRecipeFilterDtos(recipeFilterDtos);
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
    public RecipeIdResponse findRecipe(@PathVariable long recipeId){
        Recipe recipe = recipeRepository.findByID(recipeId);
        RecipeIdDto recipeIdDto = new RecipeIdDto(recipe);
        RecipeWriter recipeWriter = recipe.getRecipeWriter();

        RecipeIdResponse response = new RecipeIdResponse();
        response.setRecipeIdDto(recipeIdDto);
        response.setRecipeWriter(recipeWriter);

        return response;
    }

    @GetMapping("/recipe/blog_writer/{writer_name}")
    public RecipeWriterResponse findByBlogWriter(@PathVariable String writer_name){
        List<Recipe> recipelist = recipeRepository.getRecipeSearchWriterByBlog(writer_name);
        List<RecipeWriterDto> recipeWriterDtoList = new ArrayList<>();

        for(Recipe recipe: recipelist){
            RecipeWriterDto recipeWriterDto = new RecipeWriterDto(recipe);

            recipeWriterDtoList.add(recipeWriterDto);
        }

        RecipeWriter recipeWriter = recipeRepository.findWriter(writer_name);

        RecipeWriterResponse recipeWriterResponse = new RecipeWriterResponse();
        recipeWriterResponse.setRecipeWriterDto(recipeWriterDtoList);
        recipeWriterResponse.setRecipeWriter(recipeWriter);

        return recipeWriterResponse;
    }

    @GetMapping("/recipe/youtube_writer/{writer_name}")
    public RecipeWriterResponse findByYoutubeWriter(@PathVariable String writer_name){
        List<Recipe> recipelist = recipeRepository.getRecipeSearchWriterByYoutube(writer_name);
        List<RecipeWriterDto> recipeWriterDtoList = new ArrayList<>();

        for(Recipe recipe: recipelist){
            RecipeWriterDto recipeWriterDto = new RecipeWriterDto(recipe);

            recipeWriterDtoList.add(recipeWriterDto);
        }

        RecipeWriter recipeWriter = recipeRepository.findWriter(writer_name);

        RecipeWriterResponse recipeWriterResponse = new RecipeWriterResponse();
        recipeWriterResponse.setRecipeWriterDto(recipeWriterDtoList);
        recipeWriterResponse.setRecipeWriter(recipeWriter);

        return recipeWriterResponse;
    }

    @PutMapping("/recipe/wish/{recipeId}")
    public int saveWishRecipe(@PathVariable Long recipeId){
        String accessToken = extractToken();
        return recipeRepository.modifyWishRecipe(recipeId, accessToken);
    }
}

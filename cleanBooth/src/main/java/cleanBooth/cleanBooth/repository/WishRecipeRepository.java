package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.Mypage.Response.WishRecipeResponse;
import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.RecipeWriter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class WishRecipeRepository {

    private final EntityManager entityManager;
    private AtomicLong atomicLong = new AtomicLong();

    public List<Recipe> findLikedRecipesByUserId(Long userId){
        List<Recipe> likedRecipes = new ArrayList<>();
        String sql = "select wr from WishRecipe wr where wr.recipe.isLike = 1 and id = :userId";
        TypedQuery<Recipe> query = entityManager.createQuery(sql, Recipe.class);
        query.setParameter("userId", userId);

        List<Recipe> recipeList = query.getResultList();

        return recipeList;
    }

    public void deleteWishRecipeById(Long recipeId){
        String sql = "delete wr from WishRecipe wr where wr.recipe.id =: recipeId";
        entityManager.createQuery(sql)
                .setParameter("recipeId", recipeId)
                .executeUpdate();
    }
}

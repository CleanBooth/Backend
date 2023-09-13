package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.domain.WishItem;
import cleanBooth.cleanBooth.domain.WishRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRecipeRepository extends JpaRepository<WishRecipe, Long> {
    List<WishRecipe> findAllByUserId(Long userId);
    Optional<WishRecipe> findByRecipe_IdAndUser_Id(Long recipeId, Long UserId);

}

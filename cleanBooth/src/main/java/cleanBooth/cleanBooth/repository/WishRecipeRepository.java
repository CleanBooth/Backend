package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.domain.WishRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishRecipeRepository extends JpaRepository<WishRecipe, Long> {
//    Optional<WishRecipe> findByRecipe_IdAndUser_Id(Boolean isLike, Long UserId);
}

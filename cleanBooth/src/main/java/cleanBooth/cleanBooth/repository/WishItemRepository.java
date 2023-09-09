package cleanBooth.cleanBooth.repository;


import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.domain.User;
import cleanBooth.cleanBooth.domain.WishItem;
import cleanBooth.cleanBooth.domain.WishRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishItemRepository extends JpaRepository<WishItem, Long> {
    Optional<WishItem> findByItem_IdAndUser_Id(Long itemId, Long UserId);
}

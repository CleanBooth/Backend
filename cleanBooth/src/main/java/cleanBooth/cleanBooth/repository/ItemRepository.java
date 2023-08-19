package cleanBooth.cleanBooth.repository;


import cleanBooth.cleanBooth.domain.Category;
import cleanBooth.cleanBooth.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByCategory(Category category);

    List<Item> findByIsTestingTrue();
}

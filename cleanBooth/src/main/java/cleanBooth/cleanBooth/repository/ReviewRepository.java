package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByItem_Id(Long item_id);
}

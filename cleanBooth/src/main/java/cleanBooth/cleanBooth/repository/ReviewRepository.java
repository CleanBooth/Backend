package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

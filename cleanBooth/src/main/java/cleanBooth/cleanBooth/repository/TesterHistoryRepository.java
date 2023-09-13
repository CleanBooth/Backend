package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.domain.TesterHistory;
import cleanBooth.cleanBooth.domain.User;
import cleanBooth.cleanBooth.domain.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TesterHistoryRepository extends JpaRepository<TesterHistory, Long> {
    List<TesterHistory> findAllByUser(User user);
}

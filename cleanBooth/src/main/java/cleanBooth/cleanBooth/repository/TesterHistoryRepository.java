package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.domain.TesterHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TesterHistoryRepository extends JpaRepository<TesterHistory, Long> {


    Optional<TesterHistory> findById(Long id);

}

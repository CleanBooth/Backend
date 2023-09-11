package cleanBooth.cleanBooth.repository;


import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.domain.Tester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TesterRepository extends JpaRepository<Tester, Long> {

    Optional<Tester> findById(Long id);

    List<Tester> findByItem(Item item);

}

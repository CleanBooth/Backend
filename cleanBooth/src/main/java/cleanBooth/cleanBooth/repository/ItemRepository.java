package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.domain.Category;
import cleanBooth.cleanBooth.domain.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item){
        if (item.getId() != null){
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item find(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select m from Item m", Item.class).getResultList();
    }

}

package cleanBooth.cleanBooth.service;

import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public Item findOne(Item item){
        return itemRepository.find(item.getId());
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }


}

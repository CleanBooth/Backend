package cleanBooth.cleanBooth.service;

import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public Optional<Item> findOneById(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }


}

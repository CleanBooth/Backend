package cleanBooth.cleanBooth.tester.service;

import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.repository.ItemRepository;
import cleanBooth.cleanBooth.tester.Tester;
import cleanBooth.cleanBooth.tester.TesterRepository;
import cleanBooth.cleanBooth.tester.dto.TesterListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TesterService {
    @Autowired
    private TesterRepository testerRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<TesterListRequest> getTestedItemTestersDTO() {
        List<Item> testedItems = itemRepository.findByIsTestingTrue();
        List<TesterListRequest> testerListRequests = new ArrayList<>();

        for (Item item : testedItems) {
            List<Tester> testersForItem = testerRepository.findByItem(item);

            for (Tester tester : testersForItem) {
                TesterListRequest testerListRequest = new TesterListRequest();
                testerListRequest.setItemName(item.getName());
                testerListRequest.setItemImage(item.getImage());
                testerListRequest.setEndDate(tester.getEndDate());
                testerListRequest.setIsTesting(tester.isTesting());
                // startDate, endDate 등의 필드 설정

                testerListRequests.add(testerListRequest);
            }
        }

        return testerListRequests;
    }
}

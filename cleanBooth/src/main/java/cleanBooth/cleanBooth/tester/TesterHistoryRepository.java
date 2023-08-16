package cleanBooth.cleanBooth.tester;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TesterHistoryRepository {
    private static final Map<Long, TesterHistory> cleanTestHistory = new ConcurrentHashMap<>(); //static

    private static long sequence = 0L; //static
    // long도 atomic long을 써야 함

    // 저장하는 기능
    public TesterHistory save(TesterHistory testerHistory) {
        testerHistory.setId(++sequence);
        cleanTestHistory.put(testerHistory.getId(), testerHistory);
        return testerHistory;
    }

    // 조회
    public TesterHistory findById(Long id) {
        return cleanTestHistory.get(id);
    }

    // 전체 조회
    public List<TesterHistory> findAll() {
        return new ArrayList<>(cleanTestHistory.values()); // 안전하게 한번 감싼 것
    }


/*    // 업데이트
    public void update(Long testerId, Tester updateParam) {
        // 정석으로 개발하려면 updateParam이 아니라, ItemParamDto를 만들어서 id를 제외하고 나머지 세개만 딱 넣어놓는 것이 맞다
        // 설계상 명확한 게 낫다. 중복 vs 명확성 -> 명확성을 따르기
        Tester findTester = findById(testerId);
        findTester.setName(updateParam.getName());
        findTester.setPrice(updateParam.getPrice());
        findTester.setQuantity(updateParam.getQuantity());
    }*/

    /*    this.itemId = itemId;
            this.isTesting = isTesting;
            this.startDate = startDate;
            this.endDate = endDate;
            this.people = people;
            this.content = content;
            this.option = option;
            this.name = name;
            this.image = image;*/

    // 전체 삭제
    public void clearTesterHistory() {
        cleanTestHistory.clear();
    }
}

package cleanBooth.cleanBooth.tester.service;

import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.repository.ItemRepository;
import cleanBooth.cleanBooth.tester.Tester;
import cleanBooth.cleanBooth.tester.TesterRepository;
import cleanBooth.cleanBooth.tester.dto.TesterApplyGetDto;
import cleanBooth.cleanBooth.tester.dto.TesterDetailRequest;
import cleanBooth.cleanBooth.tester.dto.TesterListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TesterService {
    @Autowired
    private TesterRepository testerRepository;
    @Autowired
    private ItemRepository itemRepository;

    /* 체험단 리스트 가져오기 */
    public List<TesterListRequest> getAllTestersDTO() {
        List<Tester> allTesters = testerRepository.findAll();
        List<TesterListRequest> testerListRequests = new ArrayList<>();

        for (Tester tester : allTesters) {
            Item item = tester.getItem(); // Assuming there is a reference to the associated item in Tester entity

            TesterListRequest testerListRequest = new TesterListRequest();
            testerListRequest.setItemName(item.getName());
            testerListRequest.setItemImage(item.getImage());
            testerListRequest.setEndDate(tester.getEndDate());
            testerListRequest.setIsTesting(tester.isTesting());
            // startDate, endDate 등의 필드 설정

            testerListRequests.add(testerListRequest);
        }

        return testerListRequests;
    }

    /* id에 따라 체험단 자세히보기 */
    public TesterDetailRequest getTesterDetailById(Long id) throws ChangeSetPersister.NotFoundException {
        Tester tester = testerRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // Convert Tester to TesterDetailRequest using the conversion method you have implemented
        TesterDetailRequest request = convertToRequest(tester);

        return request;
    }

    // This method converts Tester to TesterDetailRequest
    private TesterDetailRequest convertToRequest(Tester tester) {
        TesterDetailRequest request = new TesterDetailRequest();
        request.setIsTesting(tester.isTesting());
        request.setItemName(tester.getItem().getName());
        request.setItemImage(tester.getItem().getImage());
        request.setContent(tester.getContent());
        request.setEndDate(tester.getEndDate());
        request.setDetailImage(tester.getDetailImage());
        return request;
    }

    /* 체험단 신청페이지 GET*/
    public TesterApplyGetDto getTesterApplyGetById(Long testerId) {
        Tester tester = testerRepository.findById(testerId).orElse(null);

        if (tester == null) {
            return null; // 없는 경우 null 반환
        }

        TesterApplyGetDto testerApplyRequest = new TesterApplyGetDto();
        testerApplyRequest.setIsTesting(tester.isTesting());
        testerApplyRequest.setItemName(tester.getItem().getName());
        testerApplyRequest.setItemImage(tester.getItem().getImage());
        testerApplyRequest.setContent(tester.getContent());
        testerApplyRequest.setEndDate(tester.getEndDate());
        testerApplyRequest.setOptions(tester.getOptions());

        return testerApplyRequest;
    }

}

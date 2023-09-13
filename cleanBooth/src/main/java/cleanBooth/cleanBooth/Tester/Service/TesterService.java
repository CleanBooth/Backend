package cleanBooth.cleanBooth.Tester.Service;

import cleanBooth.cleanBooth.domain.Item;
import cleanBooth.cleanBooth.domain.User;
import cleanBooth.cleanBooth.repository.ItemRepository;
import cleanBooth.cleanBooth.repository.UserRepository;
import cleanBooth.cleanBooth.domain.Tester;
import cleanBooth.cleanBooth.domain.TesterHistory;
import cleanBooth.cleanBooth.repository.TesterHistoryRepository;
import cleanBooth.cleanBooth.repository.TesterRepository;
import cleanBooth.cleanBooth.Tester.Dto.TesterApplyGetDto;
import cleanBooth.cleanBooth.Tester.Dto.TesterApplyPostDto;
import cleanBooth.cleanBooth.Tester.Dto.TesterDetailRequestDto;
import cleanBooth.cleanBooth.Tester.Dto.TesterListRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TesterService {
    @Autowired
    private TesterRepository testerRepository;

    @Autowired
    private TesterHistoryRepository testerHistoryRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    /* 1. 체험단 리스트 가져오기 */
    public List<TesterListRequestDto> getAllTestersDTO() {
        List<Tester> allTesters = testerRepository.findAll();
        List<TesterListRequestDto> testerListRequestDtos = new ArrayList<>();

        for (Tester tester : allTesters) {
            Item item = tester.getItem(); // Assuming there is a reference to the associated item in Tester entity

            TesterListRequestDto testerListRequestDto = new TesterListRequestDto();
            testerListRequestDto.setItemName(item.getName());
            testerListRequestDto.setItemImage(item.getImage());
            testerListRequestDto.setEndDate(tester.getEndDate());
            testerListRequestDto.setIsTesting(tester.isTesting());
            // startDate, endDate 등의 필드 설정

            testerListRequestDtos.add(testerListRequestDto);
        }

        return testerListRequestDtos;
    }

    /* 2. id에 따라 체험단 자세히보기 */
    public TesterDetailRequestDto getTesterDetailById(Long id) throws ChangeSetPersister.NotFoundException {
        Tester tester = testerRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // Convert Tester to TesterDetailRequest using the conversion method you have implemented
        TesterDetailRequestDto request = convertToRequest(tester);

        return request;
    }

    // This method converts Tester to TesterDetailRequest
    private TesterDetailRequestDto convertToRequest(Tester tester) {
        TesterDetailRequestDto request = new TesterDetailRequestDto();
        request.setIsTesting(tester.isTesting());
        request.setItemName(tester.getItem().getName());
        request.setItemImage(tester.getItem().getImage());
        request.setContent(tester.getContent());
        request.setEndDate(tester.getEndDate());
        request.setDetailImage(tester.getDetailImage());
        return request;
    }

    /* 3. 체험단 신청페이지 GET*/
    public TesterApplyGetDto getTesterApplyGetById(Long testerId, String accessToken) {
        if (accessToken == null){ //유저가 로그인 되어있지 않다면
            throw new IllegalStateException();
        }

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

    /* 4. 체험단 신청 제출 POST */
    @Transactional
    public void postApplyTester(TesterApplyPostDto applyDto) {
        // phoneNum을 사용하여 User를 찾기
        User user = userRepository.findByMobile(applyDto.getPhoneNum());

        if (user != null) {
            // Tester 엔티티를 가져와서 처리
            Tester tester = testerRepository.findById(applyDto.getTesterId()).orElse(null);

            if (tester != null) {
                // TesterHistory 엔티티를 생성하고 User 정보를 설정한다.
                TesterHistory testerHistory = new TesterHistory();
                testerHistory.setUser(user);
                testerHistory.setTester(tester);
                testerHistory.setName(applyDto.getName());
                testerHistory.setAddress(applyDto.getAddress());
                testerHistory.setMessage(applyDto.getMessage());
                testerHistory.setOptions(applyDto.getOptions());
                testerHistory.setPhoneNum(applyDto.getPhoneNum());
                // TesterHistory를 저장합니다.
                testerHistoryRepository.save(testerHistory);
            } else {
                // testerId에 해당하는 Tester가 없는 경우 예외 처리
                throw new RuntimeException("Tester not found");
            }
        } else {
            // 해당 phoneNum에 해당하는 User가 없는 경우 예외 처리
            throw new RuntimeException("User not found");
        }
    }
}

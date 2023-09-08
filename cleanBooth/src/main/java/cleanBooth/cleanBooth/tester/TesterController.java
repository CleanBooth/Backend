package cleanBooth.cleanBooth.tester;

import cleanBooth.cleanBooth.repository.ReviewRepository;
import cleanBooth.cleanBooth.repository.UserRepository;
import cleanBooth.cleanBooth.tester.dto.TesterApplyGetDto;
import cleanBooth.cleanBooth.tester.dto.TesterApplyPostDto;
import cleanBooth.cleanBooth.tester.dto.TesterDetailRequest;
import cleanBooth.cleanBooth.tester.dto.TesterListRequest;
import cleanBooth.cleanBooth.tester.service.TesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tester")
@RequiredArgsConstructor
public class TesterController {
    private final TesterRepository testerRepository;
    private final TesterHistoryRepository testerHistoryRepository;

    @Autowired
    private TesterService testerService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    /* 클린체험단 리스트 GET */
    @GetMapping
    public List<TesterListRequest> getAllTestersDTO() {
        return testerService.getAllTestersDTO();
    }

    /* 체험단 1개 상세페이지 GET*/
    @GetMapping("/{tester_id}")
    public TesterDetailRequest tester(@PathVariable Long tester_id) throws ChangeSetPersister.NotFoundException {
        return testerService.getTesterDetailById(tester_id);
    }

    /* 체험단 신청 페이지 GET*/
    @GetMapping("/apply/{tester_id}")
    public ResponseEntity<TesterApplyGetDto> getTesterApplyData(@PathVariable("tester_id") Long testerId) {
        TesterApplyGetDto testerApplyGetDto = testerService.getTesterApplyGetById(testerId);

        if (testerApplyGetDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(testerApplyGetDto);
    }

    // 수정중
    //* 체험단 신청 POST *//
    @PostMapping("/apply/{tester_id}")
    public void postTesterApply(@PathVariable("tester_id") Long testerId, @RequestBody TesterApplyPostDto applyDto) {
        applyDto.setTesterId(testerId); // URL에서 추출한 tester_id 값을 DTO에 설정
        testerService.postApplyTester(applyDto); // testerService의 postApplyTester 메서드 호출
    }
}

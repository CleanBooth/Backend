package cleanBooth.cleanBooth.tester;

import cleanBooth.cleanBooth.repository.ReviewRepository;
import cleanBooth.cleanBooth.repository.UserRepository;
import cleanBooth.cleanBooth.tester.dto.TesterApplyGetDto;
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
/*    *//* 체험단 체험 신청 POST *//*
    @PostMapping("/apply/{tester_id}")
    public ResponseEntity<String> saveTesterApplication(HttpServletRequest request, @RequestBody TesterApplyPostDto applyDto, @PathVariable("tester_id") Long testerId) {
        // TesterApplyPostDto 데이터를 가져와서 TesterHistory 도메인에 저장하는 로직을 작성합니다.
        applyDto.setTesterId(testerId);

        // 현재 인증된 사용자 정보 얻기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // User 객체에서 정보를 가져와 활용
        TesterHistory testerHistory = new TesterHistory(testerId, user,
                applyDto.getName(), applyDto.getPhoneNum(), applyDto.getAddress(),
                applyDto.getMessage(), false, applyDto.getOption(), false);

        testerHistoryRepository.save(testerHistory); // 데이터 저장

        return ResponseEntity.ok("Application saved successfully");
    }*/
/*    *//**
     * 테스트용 데이터 추가
     *//*
    @GetMapping("/item")
    public void init() {
        Tester tester1 = new Tester(1, true, LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 23),23, "content", "option");
        Tester tester2 = new Tester(2, true,LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 23),23, "content", "option");

        testerRepository.save(tester1);
        testerRepository.save(tester2);
    }*/

//    @GetMapping("/apply/{tester_id}")
//    public void init() {
//
//    }

}

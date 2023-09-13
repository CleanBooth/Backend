package cleanBooth.cleanBooth.Tester.Controller;

import cleanBooth.cleanBooth.Tester.TesterApplyGetDto;
import cleanBooth.cleanBooth.Tester.TesterApplyPostDto;
import cleanBooth.cleanBooth.Tester.TesterDetailRequestDto;
import cleanBooth.cleanBooth.Tester.TesterListRequestDto;
import cleanBooth.cleanBooth.repository.TesterHistoryRepository;
import cleanBooth.cleanBooth.repository.TesterRepository;

import cleanBooth.cleanBooth.Tester.Service.TesterService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/tester")
@RequiredArgsConstructor
public class TesterController {
    private final TesterRepository testerRepository;
    private final TesterHistoryRepository testerHistoryRepository;

    @Autowired
    private TesterService testerService;
    @Autowired
    private HttpServletRequest request;

    //액세스 토큰 추출을 위한 함수
    public String extractToken() {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 문자열 추출
            return accessToken;
        }
        return null;
    }

    /* 클린체험단 리스트 GET */
    @GetMapping
    public List<TesterListRequestDto> getAllTestersDTO() {
        return testerService.getAllTestersDTO();
    }

    /* 체험단 1개 상세페이지 GET*/
    @GetMapping("/{tester_id}")
    public TesterDetailRequestDto tester(@PathVariable Long tester_id) throws ChangeSetPersister.NotFoundException {
        return testerService.getTesterDetailById(tester_id);
    }

    /* 체험단 신청 페이지 GET*/
    @GetMapping("/apply/{tester_id}")
    public ResponseEntity<TesterApplyGetDto> getTesterApplyData(@PathVariable("tester_id") Long testerId) {
        String accessToken = extractToken();

        TesterApplyGetDto testerApplyGetDto = testerService.getTesterApplyGetById(testerId, accessToken);

        if (testerApplyGetDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(testerApplyGetDto);
    }


    //* 체험단 신청 POST *//
    @PostMapping("/apply/{tester_id}")
    public void postTesterApply(@PathVariable("tester_id") Long testerId, @RequestBody TesterApplyPostDto applyDto) {
        String accessToken = extractToken();
        applyDto.setTesterId(testerId); // URL에서 추출한 tester_id 값을 DTO에 설정
        testerService.postApplyTester(applyDto, accessToken); // testerService의 postApplyTester 메서드 호출
    }
}

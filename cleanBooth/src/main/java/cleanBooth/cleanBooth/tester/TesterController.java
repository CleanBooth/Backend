package cleanBooth.cleanBooth.tester;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tester")
@RequiredArgsConstructor
public class TesterController {
    private final TesterRepository testerRepository;
    private final TesterHistoryRepository testerHistoryRepository;

    /* 클린체험단 리스트 GET */
    @GetMapping
    public List<Tester> testers(Model model) {
        List<Tester> testers = testerRepository.findAll();
        model.addAttribute("testers", testers);
        return testers;
    }

    /* 체험단 1개 상세페이지 GET*/
    @GetMapping("/{tester_id}")
    public void tester(@PathVariable long tester_id, Model model) {
        Tester tester = testerRepository.findById(tester_id);
        model.addAttribute("tester", tester);
    }

    /* 체험단 신청 페이지 GET*/
    @GetMapping("/apply/{tester_id}")
    public void applyForm(@PathVariable long tester_id, Model model) {
        Tester tester = testerRepository.findById(tester_id);
        model.addAttribute("tester", tester);
//        TesterHistory testerHistory = testerHistoryRepository.findById(tester_id);
//        model.addAttribute("tester", tester);
    }

    /* 체험단 체험 신청 POST */
    @PostMapping("/apply/{tester_id}")
    public void apply(@PathVariable long tester_id,
                      @ModelAttribute TesterHistory testerHistory,
                      Model model) {
        testerHistoryRepository.save(testerHistory);
    }
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        Tester tester1 = new Tester(10L,false,"2023-04-23","2023-04-25",23, "content", "option", "name", "image");
        Tester tester2 = new Tester(12L,true,"2023-04-23","2023-04-25",23, "content", "option", "name", "image");

        testerRepository.save(tester1);
        testerRepository.save(tester2);
    }

//    @GetMapping("/apply/{tester_id}")
//    public void init() {
//
//    }

}

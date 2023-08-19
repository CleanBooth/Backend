package cleanBooth.cleanBooth.tester;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TesterRepositoryTest {
    TesterRepository testerRepository = new TesterRepository();

//    @AfterEach
//    void afterEach() {
//        testerRepository.clearTester();
//    }
    @Test
    void save() {
        //given
        Tester tester = new Tester(false, LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 30),23, "content", "option");

        //when
        Tester savedTester = testerRepository.save(tester);

        //then
        Tester findTester = testerRepository.findById(tester.getId());
        assertThat(findTester).isEqualTo(savedTester);
    }

    @Test
    void findAll() {
        // given
        Tester tester1 = new Tester(false,LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 23),23, "content", "option");
        Tester tester2 = new Tester(true,LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 23),23, "content", "option");

        testerRepository.save(tester1);
        testerRepository.save(tester2);

        // when
        List<Tester> result = testerRepository.findAll();
        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(tester1, tester2);
    }

    @Test
    void test3() {
        Tester tester1 = new Tester(false,LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 23),23, "content", "option");
        Tester tester2 = new Tester(true,LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 23),23, "content", "option");

        testerRepository.save(tester1);
        testerRepository.save(tester2);
    }
}
package cleanBooth.cleanBooth.Tester;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TesterRepositoryTest {
//    TesterRepository testerRepository = new TesterRepository();

//    @AfterEach
//    void afterEach() {
//        testerRepository.clearTester();
//    }
    @Test
    void save() {
/*        //given
//        Tester tester = new Tester(false, LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 30),23, "content", "option");
        Item item = new Item();
        item.setName("Test Item"); // 이름 설정 등

        Tester tester1 = new Tester(item, true, LocalDate.now(), LocalDate.now(), 5, "Test content", "Test option");
        //when
//        Tester savedTester = testerRepository.save(tester);
        Tester savedTester = testerRepository.save(tester1); // JPA 리포지토리를 통해 저장

        //then
//        Tester findTester = testerRepository.findById(tester.getId());
//        assertThat(findTester).isEqualTo(savedTester);
        Tester findTester = testerRepository.findById(tester1.getId());
        assertThat(findTester).isEqualTo(savedTester);*/
    }

    @Test
    void findAll() {
//        // given
//        Tester tester1 = new Tester(false,LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 23),23, "content", "option");
//        Tester tester2 = new Tester(true,LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 23),23, "content", "option");
//
//        testerRepository.save(tester1);
//        testerRepository.save(tester2);
//
//        // when
//        List<Tester> result = testerRepository.findAll();
//        // then
//        assertThat(result.size()).isEqualTo(2);
//        assertThat(result).contains(tester1, tester2);
    }

    @Test
    void test3() {
//        Tester tester1 = new Tester(false,LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 23),23, "content", "option");
//        Tester tester2 = new Tester(true,LocalDate.of(2023, 04, 23),LocalDate.of(2023, 04, 23),23, "content", "option");
//
//        testerRepository.save(tester1);
//        testerRepository.save(tester2);
    }
}
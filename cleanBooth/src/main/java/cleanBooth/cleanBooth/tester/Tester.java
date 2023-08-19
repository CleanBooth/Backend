package cleanBooth.cleanBooth.tester;

import cleanBooth.cleanBooth.domain.Item;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter @Setter
public class Tester {

    @Id @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private boolean isTesting;
    @JsonFormat(pattern = "yy/MM/dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yy/MM/dd")
    private LocalDate endDate;

    private int people;

    private String content;

    private String option;

    public Tester() { //기본생성자
    }

    //id 제외한 생성자
    public Tester(boolean isTesting, LocalDate startDate, LocalDate endDate, int people, String content, String option) {
        this.isTesting = isTesting;
        this.startDate = startDate;
        this.endDate = endDate;
        this.people = people;
        this.content = content;
        this.option = option;}

    public void getItem(Item item) {
        this.item = item;
    }
}

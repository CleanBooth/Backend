package cleanBooth.cleanBooth.tester;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Tester {
    private Long id;
    private Long itemId;
    private boolean isTesting;
    private String startDate;

    private String endDate;

    private int people;

    private String content;

    private String option;

    private String name;

    private String image;

    public Tester() { //기본생성자
    }

    //id 제외한 생성자
    public Tester(Long itemId, boolean isTesting, String startDate, String endDate, int people, String content, String option, String name, String image) {
        this.itemId = itemId;
        this.isTesting = isTesting;
        this.startDate = startDate;
        this.endDate = endDate;
        this.people = people;
        this.content = content;
        this.option = option;
        this.name = name;
        this.image = image;
    }
}

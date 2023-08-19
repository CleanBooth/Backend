package cleanBooth.cleanBooth.Notice.Dto;

import cleanBooth.cleanBooth.domain.Notice;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class NoticeMainDto {

    private String noticeWriter;
    private String noticeTitle;
    private Long id;
    private Long viewNum;
    private String createAt;

    public NoticeMainDto(Notice notice){
        this.id = notice.getId();
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeWriter = notice.getNoticeWriter();
        this.viewNum = notice.getViewNum();
        this. createAt = notice.getCreateAt();
    }
}

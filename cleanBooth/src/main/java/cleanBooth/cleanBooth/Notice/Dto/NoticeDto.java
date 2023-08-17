package cleanBooth.cleanBooth.Notice.Dto;

import cleanBooth.cleanBooth.domain.Notice;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeDto {

    private Long id;
    private String noticeTitle;
    private String noticeWriter;
    private String noticeContent;
    private String createAt;
    private Long viewNum;

    public NoticeDto(Notice notice){
        this.id = notice.getId();
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.noticeWriter = notice.getNoticeWriter();
        this.viewNum = notice.getViewNum();
        this.createAt = notice.getCreateAt();
    }
}

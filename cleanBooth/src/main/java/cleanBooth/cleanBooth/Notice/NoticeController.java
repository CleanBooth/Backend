package cleanBooth.cleanBooth.Notice;

import cleanBooth.cleanBooth.Notice.Dto.NoticeMainDto;
import cleanBooth.cleanBooth.Notice.Dto.SideNoticeDto;
import cleanBooth.cleanBooth.Notice.Response.NoticeContentResponse;
import cleanBooth.cleanBooth.Notice.Response.NoticeMainResponse;
import cleanBooth.cleanBooth.domain.Notice;
import cleanBooth.cleanBooth.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NoticeController {

    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public NoticeMainResponse noticeMain(){
        List<Notice> noticeMainDtoList = noticeRepository.noticeMain();
        List<NoticeMainDto> noticeMainDtos = new ArrayList<>();
        int totalCount = noticeMainDtoList.size();

        for(Notice notice: noticeMainDtoList){
            NoticeMainDto noticeMainDto = new NoticeMainDto(notice);
            noticeMainDtos.add(noticeMainDto);
        }

        NoticeMainResponse noticeMainResponse = new NoticeMainResponse();
        noticeMainResponse.setNoticeMainDtoList(noticeMainDtos);
        noticeMainResponse.setTotalCount(totalCount);

        return noticeMainResponse;
    }

    @GetMapping("/notice/title/{title_name}")
    public NoticeMainResponse noticeSearchTitle(@PathVariable String title_name){
        List<Notice> noticeList = noticeRepository.titleSearch(title_name);
        List<NoticeMainDto> noticeMainDtos = new ArrayList<>();
        int totalCount = noticeList.size();

        for(Notice notice: noticeList){
            NoticeMainDto noticeMainDto = new NoticeMainDto(notice);
            noticeMainDtos.add(noticeMainDto);
        }

        NoticeMainResponse noticeMainResponse = new NoticeMainResponse();
        noticeMainResponse.setTotalCount(totalCount);
        noticeMainResponse.setNoticeMainDtoList(noticeMainDtos);

        return noticeMainResponse;
    }

    @GetMapping("/notice/writer/{writer_name}")
    public NoticeMainResponse noticeSearchWriter(@PathVariable String writer_name){
        List<Notice> noticeList = noticeRepository.writerSearch(writer_name);
        List<NoticeMainDto> noticeMainDtos = new ArrayList<>();
        int totalCount = noticeList.size();

        for(Notice notice: noticeList){
            NoticeMainDto noticeMainDto = new NoticeMainDto(notice);
            noticeMainDtos.add(noticeMainDto);
        }

        NoticeMainResponse noticeMainResponse = new NoticeMainResponse();
        noticeMainResponse.setTotalCount(totalCount);
        noticeMainResponse.setNoticeMainDtoList(noticeMainDtos);

        return noticeMainResponse;
    }

    public void incrementViewNum(Long noticeId){
        Notice notice = noticeRepository.getById(noticeId);
        if (notice != null){
            notice.setViewNum(notice.getViewNum() + 1);
            noticeRepository.save(notice);
        }
    }

    //문제 해결 완료
    @GetMapping("/notice/{noticeNum}")
    public NoticeContentResponse noticeContent(@PathVariable Long noticeNum){
        Notice notice = noticeRepository.getContent(noticeNum);
        SideNoticeDto next = new SideNoticeDto();
        SideNoticeDto before = new SideNoticeDto();
        Long dbSize = noticeRepository.getNoticeTableSize();

        String nextTitle; String beforeTitle; Long nextId; Long beforeId;
        if (noticeNum > 0 && noticeNum < dbSize){
            nextTitle = noticeRepository.getTitle(noticeNum + 1);
            beforeTitle = noticeRepository.getTitle(noticeNum - 1);
            beforeId = noticeNum - 1;
            nextId = noticeNum +1;
        }
        else if(noticeNum == 0){
            nextTitle = noticeRepository.getTitle(noticeNum + 1);
            beforeTitle = "이전 글이 없습니다.";
            beforeId = null;
            nextId = noticeNum +1;
        }
        else{
            beforeTitle = noticeRepository.getTitle(noticeNum - 1);
            nextTitle = "다음 글이 없습니다.";
            beforeId = noticeNum -1;
            nextId = null;
        }

        next.setNoticeId(nextId);
        next.setNoticeTitle(nextTitle);

        before.setNoticeId(beforeId);
        before.setNoticeTitle(beforeTitle);

        NoticeContentResponse noticeContentResponse = new NoticeContentResponse();
        noticeContentResponse.setBeforeNotice(before);
        noticeContentResponse.setNextNotice(next);
        noticeContentResponse.setNotice(notice);

        return noticeContentResponse;
    }
}

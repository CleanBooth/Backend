package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.Notice.Response.NoticeMainResponse;
import cleanBooth.cleanBooth.domain.Notice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class NoticeRepository {

    private final EntityManager entityManager;
    private AtomicLong atomicLong = new AtomicLong();

    public List<Notice> noticeMain(){
        return entityManager.createQuery("select n from Notice n", Notice.class).getResultList();
    }

    public List<Notice> titleSearch(String title){
        String hql = "select n from Notice n where n.noticeTitle = :noticeTitle";
        TypedQuery<Notice> query = entityManager.createQuery(hql, Notice.class);
        query.setParameter("noticeTitle", title);

        List<Notice> noticeList = query.getResultList();

        return noticeList;
    }

    public List<Notice> writerSearch(String writer){
        String hql = "select n from Notice n where n.noticeWriter = :noticeWriter";
        TypedQuery<Notice> query = entityManager.createQuery(hql, Notice.class);
        query.setParameter("noticeWriter", writer);

        List<Notice> noticeList = query.getResultList();

        return noticeList;
    }

    public List<Notice> contentSearch(String content){
        String hql = "select n from Notice n where n.noticeContent like :noticeContent";
        TypedQuery<Notice> query = entityManager.createQuery(hql, Notice.class);
        query.setParameter("noticeContent", "%"+content+"%");

        List<Notice> noticeList = query.getResultList();

        return noticeList;
    }

    public Notice getById(Long id){
        return entityManager.find(Notice.class, id);
    }

    public void save(Notice notice){
        entityManager.persist(notice);
    }

    public Notice getContent(Long id){
        String hql = "select n from Notice n where n.id = :id";
        TypedQuery<Notice> query = entityManager.createQuery(hql, Notice.class);
        query.setParameter("id", id);

        Notice notice = query.getSingleResult();

        return notice;
    }

    public String getTitle(Long id){
        String hql = "select n.noticeTitle from Notice n where n.id = :id";
        TypedQuery<String> query = entityManager.createQuery(hql, String.class);
        query.setParameter("id", id);

        String title = query.getSingleResult();

        return title;
    }

    public Long getNoticeTableSize() {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM notice");
        Long result = (Long) query.getSingleResult();
        return result;
    }

}

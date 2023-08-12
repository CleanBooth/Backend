package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.Site;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class RecipeRepository {

    private final EntityManager entityManager;
    private AtomicLong atomic = new AtomicLong();

    //recipe 저장
    public void save(Recipe recipe){
        recipe.setId(atomic.incrementAndGet());
        entityManager.persist(recipe);
    }

    //ID 기준 recipe return
    public Recipe findByID(Long id){
        return entityManager.find(Recipe.class, id);
    }

    //전체 recipe return
    public List<Recipe> findAll(){
        return entityManager.createQuery("select m from Memeber m", Recipe.class).getResultList();
    }

    //스타일별 recipe return
    public List<Recipe> findByStyle(String style){
        String SQL = "SELECT r FROM Recipe WHERE style == " + style;
        return entityManager.createQuery(SQL, Recipe.class).getResultList();
    }

    //site의 writer 기준으로 recipe return
    public List<String> findBySite(Site site){
        String hql = "select r.writer from Recipe r where r.site = :site";
        TypedQuery<String> query = entityManager.createQuery(hql, String.class);
        query.setParameter("site", site);

        List<String> writers = query.getResultList();

        return writers;
    }

    //    public void update(Long id, Recipe recipe){
//        Recipe findRecipe = findByID(id);
//        findRecipe.setName(recipe.getName());
//        findRecipe.setLink(recipe.getLink());
//        findRecipe.setSite(recipe.getSite());
//        findRecipe.setStyle(recipe.getStyle());
//        findRecipe.setWriter(recipe.getWriter());
//    }

    // recipe 삭제
    public void remove(Long id){
        entityManager.remove(id);
    }

}

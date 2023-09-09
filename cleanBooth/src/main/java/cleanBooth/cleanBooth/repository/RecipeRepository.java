package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.Recipe.Dto.RecipeWriterDto;
import cleanBooth.cleanBooth.domain.Notice;
import cleanBooth.cleanBooth.domain.Recipe;
import cleanBooth.cleanBooth.domain.RecipeWriter;
import cleanBooth.cleanBooth.domain.Site;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
        String hql = "select r from Recipe r where r.id = :recipe_id";
        TypedQuery<Recipe> query = entityManager.createQuery(hql, Recipe.class);
        query.setParameter("recipe_id", id);

        Recipe recipe = query.getSingleResult();

        return recipe;
    }

    //전체 recipe return
    public List<Recipe> findAll(){
        return entityManager.createQuery("select m from Recipe m", Recipe.class).getResultList();
    }

    //스타일별 recipe return
    public List<Recipe> findByStyle(List<String> styles){
        String queryString = "SELECT r FROM Recipe r WHERE ";
        for (int i = 0; i < styles.size(); i++) {
            queryString += "r.styles LIKE :style" + i;
            if (i < styles.size() - 1) {
                queryString += " AND ";
            }
        }

        Query query = entityManager.createQuery(queryString, Recipe.class);
        for (int i = 0; i < styles.size(); i++) {
            query.setParameter("style" + i, "%" + styles.get(i) + "%");
        }

        return query.getResultList();
    }

    //site의 writer 기준으로 recipe return
    public List<String> findBySite(Site site){
        String hql = "select DISTINCT r.recipeWriter.name from Recipe r where r.site = :site";
        TypedQuery<String> query = entityManager.createQuery(hql, String.class);
        query.setParameter("site", site);

        List<String> writers = query.getResultList();

        return writers;
    }

    public List<RecipeWriter> getWriterExp(String writer_name){
        String hql = "select w from RecipeWriter w where w.name = :writer_name";
        TypedQuery<RecipeWriter> query = entityManager.createQuery(hql, RecipeWriter.class);
        query.setParameter("writer_name", writer_name);

        List<RecipeWriter> writerList = query.getResultList();

        return writerList;
    }

    //writer 검색
    public List<Recipe> getRecipeSearchWriterByYoutube(String writer_name){
        String hql = "select r from Recipe r where r.recipeWriter.name = :writer_name and r.site = 'Youtube'";
        TypedQuery<Recipe> query = entityManager.createQuery(hql, Recipe.class);
        query.setParameter("writer_name", writer_name);

        List<Recipe> recipeWriterDtos = query.getResultList();

        return recipeWriterDtos;
    }

    public List<Recipe> getRecipeSearchWriterByBlog(String writer_name){
        String hql = "select r from Recipe r where r.recipeWriter.name = :writer_name and r.site = 'Blog'";
        TypedQuery<Recipe> query = entityManager.createQuery(hql, Recipe.class);
        query.setParameter("writer_name", writer_name);

        List<Recipe> recipeWriterDtos = query.getResultList();

        return recipeWriterDtos;
    }

    public RecipeWriter findWriter(String writer_name){
        String hql = "select r from RecipeWriter r where r.name = :writer_name";
        TypedQuery<RecipeWriter> query = entityManager.createQuery(hql, RecipeWriter.class);
        query.setParameter("writer_name", writer_name);

        RecipeWriter recipeWriter = query.getSingleResult();

        return recipeWriter;
    }
}

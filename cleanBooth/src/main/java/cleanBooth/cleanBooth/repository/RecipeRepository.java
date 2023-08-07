package cleanBooth.cleanBooth.repository;

import cleanBooth.cleanBooth.domain.Recipe;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class RecipeRepository {

    @Autowired
    private EntityManager entityManager;
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
        return entityManager.createQuery("select r from Recipe r", Recipe.class).getResultList();
    }

    //스타일별 recipe return
    public List<Recipe> findByStyle(String style){
        String SQL = "SELECT r FROM recipe WHERE style == " + style;
        return entityManager.createQuery(SQL, Recipe.class).getResultList();
    }

    //site 기준으로 recipe return
    public List<Recipe> findBySite(String site){
        List<Recipe> listRecipe = new ArrayList<>();
        if(site.equals("Youtube")){
            String SQL = "SELECT r FROM recipe WHERE site == 'Youtube'";
            try{
                listRecipe = entityManager.createQuery(SQL, Recipe.class).getResultList();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return listRecipe;

        }
        else if(site.equals("blog")){
            String SQL = "SELECT r FROM recipe WHERE site == 'blog'";
            try{
                listRecipe = entityManager.createQuery(SQL, Recipe.class).getResultList();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return listRecipe;
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

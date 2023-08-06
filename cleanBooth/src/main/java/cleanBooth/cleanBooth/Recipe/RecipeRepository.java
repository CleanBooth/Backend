package cleanBooth.cleanBooth.Recipe;

import cleanBooth.cleanBooth.domain.Recipe;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class RecipeRepository {

    private EntityManager entityManager;
    private AtomicLong atomic = new AtomicLong();

    public void save(Recipe recipe){
        recipe.setId(atomic.incrementAndGet());
        entityManager.persist(recipe);
    }

    public Recipe findByID(Long id){
        return entityManager.find(Recipe.class, id);
    }

    public List<Recipe> findAll(){
        return entityManager.createQuery("select m from Memeber m", Recipe.class).getResultList();
    }

    public List<Recipe> findByStyle(String style){
        String SQL = "SELECT r FROM Recipe WHERE style == " + style;
        return entityManager.createQuery(SQL, Recipe.class).getResultList();
    }

    public List<Recipe> findBySite(String site){
        List<Recipe> listRecipe = new ArrayList<>();
        if(site.equals("Youtube")){
            String SQL = "SELECT r FROM Recipe WHERE site == 'Youtube'";
            try{
                listRecipe = entityManager.createQuery(SQL, Recipe.class).getResultList();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return listRecipe;

        }
        else if(site.equals("blog")){
            String SQL = "SELECT r FROM Recipe WHERE site == 'blog'";
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
    public void remove(Long id){
        entityManager.remove(id);
    }

}

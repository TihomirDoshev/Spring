import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class _02_ChangeCasing {
    public static void main (String[]args){

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Town> resultList = entityManager.createQuery("SELECT t FROM Town t", Town.class).getResultList();

        for (Town town : resultList) {
        if (town.getName().length() <= 5){
            town.setName(town.getName().toUpperCase());
            entityManager.persist(town);
        }
        }

        entityManager.getTransaction().commit();

    }
}

package Task_02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;

public class Main_02 {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Customer customer = new Customer
                ("customer","customer","123");
        Product product = new Product("product",10, BigDecimal.TEN);
        StoreLocation storeLocation = new StoreLocation("location");
        Sale sale = new Sale(product,customer,storeLocation);

        entityManager.persist(product);
        entityManager.persist(customer);
        entityManager.persist(storeLocation);
        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}

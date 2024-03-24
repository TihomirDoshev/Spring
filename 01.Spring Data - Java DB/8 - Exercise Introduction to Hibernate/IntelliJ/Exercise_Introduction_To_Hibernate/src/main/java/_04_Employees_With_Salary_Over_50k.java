import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

public class _04_Employees_With_Salary_Over_50k {
    public static void main (String[]args){
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List <Employee>resultList = entityManager.createQuery
                ("FROM Employee e WHERE salary > 50000", Employee.class).getResultList();

        for (Employee employee :resultList) {
            System.out.println(employee.getFirstName());
        }


        entityManager.getTransaction().commit();

    }
}

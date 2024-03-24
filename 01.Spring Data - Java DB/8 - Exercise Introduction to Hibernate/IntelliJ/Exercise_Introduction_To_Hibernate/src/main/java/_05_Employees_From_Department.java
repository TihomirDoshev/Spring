import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _05_Employees_From_Department {
    public static void main (String[]args){
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Employee> resultList = entityManager.createQuery("SELECT e FROM Employee e JOIN e.department d" +
                " WHERE d.id = 6" +
                " ORDER BY e.salary , e.id", Employee.class).getResultList();


        for (Employee employee:resultList) {
            System.out.printf("%s %s from Research and Development - $%.2f%n"
            , employee.getFirstName(), employee.getLastName(), employee.getSalary());

        }


        entityManager.getTransaction().commit();

    }
}

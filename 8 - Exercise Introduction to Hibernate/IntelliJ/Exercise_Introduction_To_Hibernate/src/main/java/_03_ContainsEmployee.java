import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.List;
import java.util.Scanner;

public class _03_ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

        String [] input = scanner.nextLine().split("\\s+");
        List <Employee>resultList = entityManager
                .createQuery("SELECT e FROM Employee e" +
                " WHERE firstName = :first_name AND " +
                " lastName = : last_name", Employee.class)
                .setParameter("first_name", input[0])
                .setParameter("last_name",input[1])
                .getResultList();
//
        System.out.println(resultList.size() > 0 ? "Yes" : "No");
        entityManager.getTransaction().commit();
    }
}

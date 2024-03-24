import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class _06_AddingNewAddress {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Town town = entityManager.find(Town.class, 32);
        Address address = new Address();
        address.setText("Vitoshka 15");
        address.setTown(town);
        entityManager.persist(address);

        Scanner scanner = new Scanner(System.in);
        String inputName = scanner.nextLine();

        List<Employee> resultList = entityManager
                .createQuery("SELECT e FROM Employee e WHERE lastName = :  last_name", Employee.class)
                .setParameter("last_name", inputName).getResultList();
        if (!resultList.isEmpty()){
            Employee employee = resultList.get(0);
            employee.setAddress(address);
            entityManager.persist(employee);
        }


            entityManager.getTransaction().commit();
    }
}


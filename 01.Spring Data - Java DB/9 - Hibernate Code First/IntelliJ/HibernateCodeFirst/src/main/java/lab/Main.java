package lab;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab.inheritance.Bike;
import lab.inheritance.Car;
import lab.inheritance.Plane;
import lab.inheritance.Truck;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory main = Persistence.createEntityManagerFactory("main");
        EntityManager entityManager = main.createEntityManager();
        entityManager.getTransaction().begin();

        Vehicle car =  new Car("Corsa", BigDecimal.TEN,"Petrol",5);
        Vehicle bike = new Bike("BMX",BigDecimal.ONE,"None");
        Vehicle plane = new Plane("Boeing",BigDecimal.TEN,"Cerosine",100);
        Vehicle truck = new Truck("Scania",BigDecimal.ONE,"Diesel",40);

        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

package relations.manyToMany;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lab.Vehicle;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "trucks")
public class manyToManyTruck extends Vehicle {

    private static final String TRUCK_TYPE = "TRUCK";
    @Column(name = "load_capacity ")
    private double loadCapacity;

    @ManyToMany
    private List<Driver> drivers;

    public manyToManyTruck(){

    }
    public manyToManyTruck(String model, BigDecimal price, String fuelType, double loadCapacity) {
        super(TRUCK_TYPE, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }
}

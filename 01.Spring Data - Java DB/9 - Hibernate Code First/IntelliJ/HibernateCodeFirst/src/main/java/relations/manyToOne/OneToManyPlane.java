package relations.manyToOne;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lab.Vehicle;

import java.math.BigDecimal;


@Entity
@Table(name = "planes")
public class OneToManyPlane extends Vehicle {

    private static final String PLANE_TYPE = "PLANE";
    @Column(name = "passenger_capacity")
    private int passengerCapacity;
    @ManyToOne
    private Company owner;

    public OneToManyPlane() {

    }

    public OneToManyPlane(String model, BigDecimal price, String fuelType, int passengerCapacity) {
        super(PLANE_TYPE, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }
}

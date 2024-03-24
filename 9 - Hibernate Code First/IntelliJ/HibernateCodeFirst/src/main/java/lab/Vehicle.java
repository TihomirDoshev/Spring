package lab;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Vehicle {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
@Basic
    private String type;
@Basic
    private String model;
@Basic
    private BigDecimal price;
@Column(name = "fuel_type")
    private  String fuelType;
protected Vehicle(){

}
    protected Vehicle(String type){
this.type = type;
    }

    protected Vehicle(String type, String model, BigDecimal price, String fuelType) {
        this.type = type;
        this.model = model;
        this.price = price;
        this.fuelType = fuelType;
    }
}

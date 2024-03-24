package relations.oneToOne;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "plate_numbers")

public class PlateNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private BigInteger id;
    private String number;

    public PlateNumber() {

    }

    public PlateNumber(String number) {
        this.number = number;
    }
}

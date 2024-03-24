package Task_01;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "wizards_deposits")
public class Wizard_Deposits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(name = "first_name",length = 50)
    private String firstName;
    @Column(name = "last_name",length = 60, nullable = false)
    private String lastName;
    @Column(length = 1000)
    private String notes;

    private int age;
    @Column(name = "magic_wand_creator",length = 100)
    private String magicWandCreator;
    @Column(name = "magic_wand_size")
    private short magicWandSize;
    @Column(name = "deposit_group",length = 50)
    private String depositGroup;
    @Column(name = "deposit_start_date",nullable = false)
    private LocalDateTime depositStartDate;
    @Column(name = "deposit_amount",nullable = false)
    private float depositAmount;
    @Column(name = "deposit_interest",nullable = false)
    private float depositInterest;

    @Column(name = "deposit_charge",nullable = false)
    private float depositCharge;
    @Column(name = "is_deposit_expired")
    private boolean depositExpired;

    public Wizard_Deposits() {
    }



    public Wizard_Deposits(int id, String firstName, String lastName, String notes, int age, String magicWandCreator, short magicWandSize, String depositGroup, LocalDateTime depositStartDate, float depositAmount, float depositInterest, float depositCharge, boolean depositExpired) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.age = age;
        this.magicWandCreator = magicWandCreator;
        this.magicWandSize = magicWandSize;
        this.depositGroup = depositGroup;
        this.depositStartDate = depositStartDate;
        this.depositAmount = depositAmount;
        this.depositInterest = depositInterest;
        this.depositCharge = depositCharge;
        this.depositExpired = depositExpired;
    }
}

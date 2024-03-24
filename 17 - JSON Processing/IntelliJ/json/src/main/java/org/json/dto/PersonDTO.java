package org.json.dto;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PersonDTO {
    @Expose
    private String firstName;
    private String lastName;
    private int age;
    @Expose
    private boolean isMarried;



    List<Integer>lotteryNumbers;

    private AddressDTO addressDTO;

    public PersonDTO(String firstName, String lastName, int age, boolean isMarried
            ,List<Integer>lotteryNumbers,AddressDTO addressDTO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isMarried = isMarried;
        this.lotteryNumbers = lotteryNumbers;
        this.addressDTO = addressDTO;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", isMarried=" + isMarried +
                ", lotteryNumbers=" + lotteryNumbers +
                ", addressDTO=" + addressDTO +
                '}';
    }
}

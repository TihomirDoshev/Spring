package softuni.exam.models.dto.xml;


import softuni.exam.models.entity.Volcano;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "volcanologist")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistsSeedDto {
    @XmlElement(name = "first_name")
    @Size(min = 2,max = 30)

    private String firstName;
    @XmlElement(name = "last_name")
    @Size(min = 2,max = 30)

    private String lastName;
    @XmlElement(name = "salary")
    @Positive

    private double salary;
    @XmlElement(name = "age")

    @Min(18)
    @Max(80)
    private  int age;
    @XmlElement(name = "exploring_from")
    private String exploringFrom;
    @XmlElement(name = "exploring_volcano_id")

    private long volcanoId;

    public VolcanologistsSeedDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(String exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

    public long getVolcanoId() {
        return volcanoId;
    }

    public void setVolcanoId(long volcanoId) {
        this.volcanoId = volcanoId;
    }
}

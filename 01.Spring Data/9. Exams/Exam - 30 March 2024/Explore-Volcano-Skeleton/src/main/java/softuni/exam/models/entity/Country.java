package softuni.exam.models.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity{
    @Column(nullable = false,unique = true)
    private String name;
    @Column
    private String capital;
    @OneToMany(mappedBy = "country")
    private Set<Volcano> volcanoSet;

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Set<Volcano> getVolcanoSet() {
        return volcanoSet;
    }

    public void setVolcanoSet(Set<Volcano> volcanoSet) {
        this.volcanoSet = volcanoSet;
    }
}

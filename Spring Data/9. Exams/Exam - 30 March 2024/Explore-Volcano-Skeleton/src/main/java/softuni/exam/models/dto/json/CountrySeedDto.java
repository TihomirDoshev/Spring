package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountrySeedDto {
    @Expose
    @NotNull
    @Size(min = 3,max = 30)
    private String name;
    @Expose
    @Size(min = 3,max = 30)
    private String capital;

    public CountrySeedDto() {
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
}

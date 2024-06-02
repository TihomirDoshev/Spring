package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.Country;
import softuni.exam.models.enums.VolcanoType;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class VolcanoSeedDto {
    @Expose
    @NotNull
    @Size(min = 2,max = 30)
    private String name;
    @Expose
    @NotNull
    @Positive
    private int elevation;
    @Expose
    @Enumerated
    private VolcanoType volcanoType;
    @Expose
    @NotNull
    private boolean isActive;
    @Expose
    private String lastEruption;
    @Expose
    @NotNull
    private long country;

    public VolcanoSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public VolcanoType getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(VolcanoType volcanoType) {
        this.volcanoType = volcanoType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(String lastEruption) {
        this.lastEruption = lastEruption;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }
}

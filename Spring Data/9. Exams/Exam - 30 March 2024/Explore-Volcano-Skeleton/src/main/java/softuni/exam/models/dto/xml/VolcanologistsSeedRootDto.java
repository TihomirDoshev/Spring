package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "volcanologists")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistsSeedRootDto {
    @XmlElement(name = "volcanologist")
    private List<VolcanologistsSeedDto> volcanologistsSeedDtoSet;

    public VolcanologistsSeedRootDto() {
    }

    public List<VolcanologistsSeedDto> getVolcanologistsSeedDtoSet() {
        return volcanologistsSeedDtoSet;
    }

    public void setVolcanologistsSeedDtoSet(List<VolcanologistsSeedDto> volcanologistsSeedDtoSet) {
        this.volcanologistsSeedDtoSet = volcanologistsSeedDtoSet;
    }
}

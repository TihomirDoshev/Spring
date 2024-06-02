package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastRootDto {
    @XmlElement(name = "forecast")
    private List<ForecastDto> forecastDto;

    public ForecastRootDto() {
    }

    public List<ForecastDto> getForecastDto() {
        return forecastDto;
    }

    public void setForecastDto(List<ForecastDto> forecastDto) {
        this.forecastDto = forecastDto;
    }
}

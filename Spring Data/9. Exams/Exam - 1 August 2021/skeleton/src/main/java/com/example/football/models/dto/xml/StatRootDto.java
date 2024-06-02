package com.example.football.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatRootDto {
    @XmlElement(name = "stat")
    private List<StatDto> stats;

    public StatRootDto() {
    }

    public List<StatDto> getStats() {
        return stats;
    }

    public void setStats(List<StatDto> stats) {
        this.stats = stats;
    }
}

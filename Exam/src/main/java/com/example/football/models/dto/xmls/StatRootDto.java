package com.example.football.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatRootDto {

    @XmlElement(name = "stat")
    private List<StatsDto> stats;

    public StatRootDto() {
    }

    public List<StatsDto> getStats() {
        return stats;
    }

    public void setStats(List<StatsDto> stats) {
        this.stats = stats;
    }
}

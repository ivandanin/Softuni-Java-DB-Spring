package softuni.exam.models.dtos.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownXMLDto {
    @XmlElement
    private String name;

    public TownXMLDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

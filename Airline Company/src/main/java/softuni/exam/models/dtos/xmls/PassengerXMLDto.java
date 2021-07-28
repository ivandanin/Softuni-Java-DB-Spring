package softuni.exam.models.dtos.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passenger")
@XmlAccessorType(XmlAccessType.FIELD)
public class PassengerXMLDto {
    @XmlElement
    private String email;

    public PassengerXMLDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

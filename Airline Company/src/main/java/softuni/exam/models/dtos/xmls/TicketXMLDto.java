package softuni.exam.models.dtos.xmls;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketXMLDto {

    @XmlElement(name = "serial-number")
    private String serialNumber;

    public TicketXMLDto() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}

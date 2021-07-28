package softuni.exam.models.dtos.xmls;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketDto {

    @XmlElement(name = "serial-number")
    private String serialNumber;
    @XmlElement
    private BigDecimal price;
    @XmlElement(name = "take-off")
    private String takeoff;
    @XmlElement(name = "from-town")
    private TownXMLDto fromTown;
    @XmlElement(name = "to-town")
    private TownXMLDto toTown;
    @XmlElement(name = "passenger")
    private PassengerXMLDto passengerDto;
    @XmlElement(name = "plane")
    private PlaneDto planeDto;

    public TicketDto() {
    }

    @Length(min = 2)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @DecimalMin(value = "0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(String takeoff) {
        this.takeoff = takeoff;
    }

    public TownXMLDto getFromTown() {
        return fromTown;
    }

    public void setFromTown(TownXMLDto fromTown) {
        this.fromTown = fromTown;
    }

    public TownXMLDto getToTown() {
        return toTown;
    }

    public void setToTown(TownXMLDto toTown) {
        this.toTown = toTown;
    }

    public PassengerXMLDto getPassengerDto() {
        return passengerDto;
    }

    public void setPassengerDto(PassengerXMLDto passengerDto) {
        this.passengerDto = passengerDto;
    }

    public PlaneDto getPlaneDto() {
        return planeDto;
    }

    public void setPlaneDto(PlaneDto planeDto) {
        this.planeDto = planeDto;
    }
}

package softuni.exam.models.dtos.xmls;

import org.hibernate.validator.constraints.Length;
import softuni.exam.models.dtos.jsons.PassengerDto;
import softuni.exam.models.dtos.jsons.TownDto;

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
    private TownDto fromTown;
    @XmlElement(name = "to-town")
    private TownDto toTown;
    @XmlElement
    private PassengerDto passengerDto;
    @XmlElement
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

    public TownDto getFromTown() {
        return fromTown;
    }

    public void setFromTown(TownDto fromTown) {
        this.fromTown = fromTown;
    }

    public TownDto getToTown() {
        return toTown;
    }

    public void setToTown(TownDto toTown) {
        this.toTown = toTown;
    }

    public PassengerDto getPassengerDto() {
        return passengerDto;
    }

    public void setPassengerDto(PassengerDto passengerDto) {
        this.passengerDto = passengerDto;
    }

    public PlaneDto getPlaneDto() {
        return planeDto;
    }

    public void setPlaneDto(PlaneDto planeDto) {
        this.planeDto = planeDto;
    }
}

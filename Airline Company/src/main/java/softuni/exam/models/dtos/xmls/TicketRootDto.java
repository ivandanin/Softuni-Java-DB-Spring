package softuni.exam.models.dtos.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketRootDto {

    @XmlElement
    private Set<TicketDto> tickets;

    public TicketRootDto() {
    }

    public Set<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketDto> tickets) {
        this.tickets = tickets;
    }
}

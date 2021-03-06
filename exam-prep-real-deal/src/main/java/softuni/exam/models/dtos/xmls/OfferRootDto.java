package softuni.exam.models.dtos.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferRootDto {

    @XmlElement(name = "offer")
    private List<OfferDto> offerDtos;

    public OfferRootDto() {
    }

    public List<OfferDto> getOfferDtos() {
        return offerDtos;
    }

    public void setOfferDtos(List<OfferDto> offerDtos) {
        this.offerDtos = offerDtos;
    }
}

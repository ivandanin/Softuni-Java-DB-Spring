package softuni.exam.models.dtos.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerRootDto {
    @XmlElement(name = "seller")
    private List<SellerDto> sellerRootDtos;

    public SellerRootDto() {
    }

    public List<SellerDto> getSellerRootDtos() {
        return sellerRootDtos;
    }

    public void setSellerRootDtos(List<SellerDto> sellerRootDtos) {
        this.sellerRootDtos = sellerRootDtos;
    }
}

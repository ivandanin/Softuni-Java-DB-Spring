package entities.paymentSystem;

import entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BaseEntity {

    private CardType cardType;
    private Integer expiringMonth;
    private Integer expiringYear;

    public CreditCard() {
    }

    @Enumerated(EnumType.STRING)
    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Column(name = "exp_month")
    public Integer getExpiringMonth() {
        return expiringMonth;
    }

    public void setExpiringMonth(Integer expiringMonth) {
        this.expiringMonth = expiringMonth;
    }

    @Column(name = "exp_year")
    public Integer getExpiringYear() {
        return expiringYear;
    }

    public void setExpiringYear(Integer expiringYear) {
        this.expiringYear = expiringYear;
    }
}

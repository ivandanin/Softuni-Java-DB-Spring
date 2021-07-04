package entities;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    private final int seats;

    public Car() {
        super("CAR");
        this.seats = 5;
    }
}

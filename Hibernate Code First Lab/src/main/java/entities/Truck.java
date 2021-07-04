package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle {

    private final double loadCapacity;

    public Truck() {
        super("TRUCK");
        this.loadCapacity = 2000;
    }
}

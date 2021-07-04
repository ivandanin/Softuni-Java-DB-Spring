package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "planes")
public class Plane extends Vehicle {

    private int passengerCapacity;

    public Plane(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Plane() {

    }
}

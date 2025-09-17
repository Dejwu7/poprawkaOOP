package org.example;

import java.io.Serializable;

public class CarObject implements Serializable {
    public String manufacturer;
    public String name;
    public int productionYear;
    public int topSpeed;
    public transient int id; //słowo transient sprawia, że ta wartość nie będzie zapisywana

    public CarObject(String manufacturer, String name, int productionYear, int topSpeed, int id) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.productionYear = productionYear;
        this.topSpeed = topSpeed;
        this.id = id;
    }

    @Override
    public String toString() {
        return "CarObject{" +
                "manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                ", productionYear=" + productionYear +
                ", topSpeed=" + topSpeed +
                ", id=" + id +
                '}';
    }
}

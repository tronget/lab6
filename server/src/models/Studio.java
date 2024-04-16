package models;

import utility.Validatable;

import java.io.Serializable;
import java.util.Objects;

public class Studio implements Validatable, Serializable {
    private String name; //Поле не может быть null
    private String address; //Поле может быть null

    public Studio(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean validate() {
        return name != null &&
                address != null;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studio studio = (Studio) o;
        return Objects.equals(name, studio.name) && Objects.equals(address, studio.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}

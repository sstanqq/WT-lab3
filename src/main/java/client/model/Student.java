package main.java.client.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student implements Serializable {
    private int id;
    private String name;
    private String characteristic;
    private LocalDate birthday;
    private LocalDateTime lastModification;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getLastModification() {
        return lastModification;
    }

    public void setLastModification(LocalDateTime lastModification) {
        this.lastModification = lastModification;
    }

    @Override
    public String toString() {
        return id + ": Student{" +
                "name='" + name + '\'' +
                ", characteristic='" + characteristic + '\'' +
                ", birthday=" + birthday +
                ", lastModification=" + lastModification +
                '}';
    }
}

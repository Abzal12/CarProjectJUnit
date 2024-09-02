package org.abzal1.model.car;

import jakarta.persistence.*;
import org.abzal1.model.user.User;

import java.time.LocalDate;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_type")
    private Type type;

    @Column(name = "car_class")
    private String carClass;

    @Column(name = "start_date")
    private LocalDate startDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Car() {
    }

    public Car(Long id, Type type, String carClass, LocalDate startDate, User user) {
        this.id = id;
        this.type = type;
        this.carClass = carClass;
        this.startDate = startDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carType=" + type +
                ", carClass='" + carClass + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}

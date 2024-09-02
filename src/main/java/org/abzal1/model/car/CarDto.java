package org.abzal1.model.car;

import java.time.LocalDate;

public class CarDto {
    private Long id;
    private Long userId;
    private Type type;
    private String carClass;
    private LocalDate startDate;

    public CarDto(Long id, Long userId, Type type, String carClass, LocalDate startDate) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.carClass = carClass;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Type getCarType() {
        return type;
    }

    public void setCarType(Type type) {
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
}

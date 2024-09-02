package org.abzal1.service;

import org.abzal1.model.car.Car;
import org.abzal1.model.car.CarDto;
import org.abzal1.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    @Autowired(required = false)
    private final String thisIsMyFirstConditionalBean;

    public CarService(CarRepository carRepository, String thisIsMyFirstConditionalBean) {
        this.carRepository = carRepository;
        this.thisIsMyFirstConditionalBean = thisIsMyFirstConditionalBean;
    }

    @Transactional
    public Car saveCar(Car car) {
        if (car == null) {
            throw new NullPointerException();
        }
        return carRepository.save(car);
    }

    @Transactional
    public CarDto getCarById(Long carId) {
        Optional<Car> car = Optional.empty();
        if (carId != null) {
           car = carRepository.findById(carId);
        }
        
        return car.map(value -> new CarDto(
                value.getId(),
                value.getUser().getId(),
                value.getType(),
                value.getCarClass(),
                value.getStartDate()
        )).orElse(null);
    }

    @Transactional
    public List<CarDto> getCarsByUserId(Long userId) {

        if (userId == null) {
            return List.of();
        }
        List<Car> cars = carRepository.findByUserId(userId);
        return cars.stream()
                .map(car -> new CarDto(
                        car.getId(),
                        car.getUser().getId(),
                        car.getType(),
                        car.getCarClass(),
                        car.getStartDate()
                ))
                .toList();
    }

    @Transactional
    public void printCustomConditionalBean() {
        System.out.println(thisIsMyFirstConditionalBean);
    }

}


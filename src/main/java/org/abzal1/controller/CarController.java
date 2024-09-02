package org.abzal1.controller;

import org.abzal1.model.car.Car;
import org.abzal1.model.car.CarDto;
import org.abzal1.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/save")
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        Car savedCar = carService.saveCar(car);
        if (savedCar != null) {
            return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        CarDto carDto = carService.getCarById(id);
        if (carDto != null) {
            return new ResponseEntity<>(carDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<CarDto>> getCarByUserId(@PathVariable Long id) {
        List<CarDto> carDtos = carService.getCarsByUserId(id);
        if (carDtos != null) {
            return new ResponseEntity<>(carDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

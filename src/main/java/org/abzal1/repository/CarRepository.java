package org.abzal1.repository;

import org.abzal1.model.car.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {

    Optional<Car> findById(Long carId);
    List<Car> findByUserId(Long userId);
}

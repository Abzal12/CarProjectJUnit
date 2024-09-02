package service;

import org.abzal1.model.car.Car;
import org.abzal1.model.car.CarDto;
import org.abzal1.model.car.Type;
import org.abzal1.model.user.User;
import org.abzal1.repository.CarRepository;
import org.abzal1.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepository carRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Abzal", LocalDate.now());
    }

    @Test
    void saveCar_positive() {
        Car car = new Car(1L, Type.HATCHBACK, "Average", LocalDate.now(), user);
        when(carRepository.save(car)).thenReturn(car);

        Car savedCar = carService.saveCar(car);

        assertNotNull(savedCar);
        assertEquals(1L, savedCar.getId());
        verify(carRepository, times(1)).save(car);
    }

    @Test
    void saveCar_negative() {
        Car car = null;

        assertThrows(NullPointerException.class, () -> carService.saveCar(car));
        verify(carRepository, never()).save(any(Car.class));
    }

    @Test
    void saveCar_cornerCase() {
        Car car = new Car(null, Type.SPORT_UTILITY_VEHICLE, "", LocalDate.now(), user);
        when(carRepository.save(car)).thenReturn(car);

        Car savedCar = carService.saveCar(car);

        assertNotNull(savedCar);
        assertNull(savedCar.getId());
        assertEquals("", car.getCarClass());
        verify(carRepository, times(1)).save(car);
    }

    @Test
    void getCarById_positive() {
        Car car = new Car(1L, Type.SEDAN, "Luxury", LocalDate.now(), user);
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        CarDto foundCar = carService.getCarById(1L);

        assertNotNull(foundCar);
        assertEquals(Type.SEDAN, car.getType());
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void getCarById_negative() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        CarDto foundCar = carService.getCarById(1L);
        assertNull(foundCar);
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void getCarById_cornerCase() {
        CarDto foundCar = carService.getCarById(null);

        assertNull(foundCar);
        verify(carRepository, never()).findById(any());
    }

    @Test
    void getCarsByUserId_positive() {
        Car car1 = new Car(1L, Type.HATCHBACK, "Average", LocalDate.now(), user);
        Car car2 = new Car(1L, Type.COUPE, "Average", LocalDate.now(), user);
        List<Car> cars = List.of(car1, car2);
        when(carRepository.findByUserId(1L)).thenReturn(cars);

        List<CarDto> foundCars = carService.getCarsByUserId(1L);
        assertNotNull(foundCars);
        assertEquals(2, foundCars.size());
        verify(carRepository, times(1)).findByUserId(1L);
    }

    @Test
    void getCarsByUserId_negative() {
        when(carRepository.findByUserId(1L)).thenReturn(Collections.emptyList());

        List<CarDto> foundCars = carService.getCarsByUserId(1L);

        assertTrue(foundCars.isEmpty());
        verify(carRepository, times(1)).findByUserId(1L);
    }

    @Test
    void getCarsByUserId_cornerCase() {
        List<CarDto> foundCars = carService.getCarsByUserId(null);

        assertTrue(foundCars.isEmpty());
        verify(carRepository, never()).findByUserId(any());
    }
}

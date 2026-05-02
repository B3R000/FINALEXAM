package ge.tsu.mercedes.controller.api;

import ge.tsu.mercedes.dto.CarDto;
import ge.tsu.mercedes.model.Car;
import ge.tsu.mercedes.service.CarCategoryService;
import ge.tsu.mercedes.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarApiController {

    private final CarService carService;
    private final CarCategoryService categoryService;

    public CarApiController(CarService carService, CarCategoryService categoryService) {
        this.carService = carService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CarDto> findAll() {
        return carService.findAll().stream()
                .map(CarDto::fromEntity)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto create(@Valid @RequestBody CarDto dto) {
        Car car = dto.toEntity();
        car.setCategory(categoryService.findById(dto.categoryId()));
        return CarDto.fromEntity(carService.save(car, null));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        carService.delete(id);
    }
}

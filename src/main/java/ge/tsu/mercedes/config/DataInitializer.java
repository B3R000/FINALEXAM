package ge.tsu.mercedes.config;

import ge.tsu.mercedes.model.Car;
import ge.tsu.mercedes.model.CarCategory;
import ge.tsu.mercedes.service.CarCategoryService;
import ge.tsu.mercedes.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final CarService carService;
    private final CarCategoryService categoryService;

    public DataInitializer(CarService carService, CarCategoryService categoryService) {
        this.carService = carService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {
        if (!carService.findAll().isEmpty()) {
            return;
        }

        CarCategory sedan = new CarCategory();
        sedan.setName("Sedan");
        sedan.setDescription("Comfortable premium city and business cars.");
        categoryService.save(sedan);

        CarCategory suv = new CarCategory();
        suv.setName("SUV");
        suv.setDescription("Luxury cars with higher clearance and more space.");
        categoryService.save(suv);

        createCar("Mercedes-Benz C-Class", 2022, "Black", new BigDecimal("42000"), "sales@mercedes-showroom.ge", sedan);
        createCar("Mercedes-Benz E-Class", 2023, "Silver", new BigDecimal("61000"), "sales@mercedes-showroom.ge", sedan);
        createCar("Mercedes-Benz GLE", 2024, "White", new BigDecimal("83500"), "sales@mercedes-showroom.ge", suv);

        log.info("Demo showroom data has been initialized");
    }

    private void createCar(String model, int year, String color, BigDecimal price, String email, CarCategory category) {
        Car car = new Car();
        car.setModel(model);
        car.setYear(year);
        car.setColor(color);
        car.setPrice(price);
        car.setContactEmail(email);
        car.setCategory(category);
        carService.save(car, null);
    }
}

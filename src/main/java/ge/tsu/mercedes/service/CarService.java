package ge.tsu.mercedes.service;

import ge.tsu.mercedes.config.ShowroomProperties;
import ge.tsu.mercedes.model.Car;
import ge.tsu.mercedes.repository.CarRepository;
import org.springframework.data.domain.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private static final Logger log = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;
    private final ShowroomProperties properties;

    public CarService(CarRepository carRepository, ShowroomProperties properties) {
        this.carRepository = carRepository;
        this.properties = properties;
    }

    public List<Car> findAll() {
        log.info("Loading all cars");
        return carRepository.findAll();
    }

    public List<Car> findFeatured() {
        int limit = Math.max(properties.getFeaturedLimit(), 1);
        return carRepository.findAllByOrderByIdDesc(PageRequest.of(0, limit));
    }

    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Car was not found: {}", id);
                    return new IllegalArgumentException("Car not found");
                });
    }

    public Car save(Car car, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            car.setImageFileName(storeImage(image));
        }
        log.info("Saving car {}", car.getModel());
        return carRepository.save(car);
    }

    public void delete(Long id) {
        log.warn("Deleting car with id {}", id);
        carRepository.deleteById(id);
    }

    private String storeImage(MultipartFile image) {
        try {
            String originalName = StringUtils.cleanPath(image.getOriginalFilename() == null ? "car-image" : image.getOriginalFilename());
            String fileName = UUID.randomUUID() + "-" + originalName;
            Path uploadPath = Path.of(properties.getUploadDir());
            Files.createDirectories(uploadPath);
            Files.copy(image.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            log.error("Could not store uploaded image", ex);
            throw new IllegalStateException("Could not store uploaded image", ex);
        }
    }
}

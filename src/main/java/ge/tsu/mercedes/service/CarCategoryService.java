package ge.tsu.mercedes.service;

import ge.tsu.mercedes.model.CarCategory;
import ge.tsu.mercedes.repository.CarCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarCategoryService {

    private static final Logger log = LoggerFactory.getLogger(CarCategoryService.class);

    private final CarCategoryRepository categoryRepository;

    public CarCategoryService(CarCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CarCategory> findAll() {
        log.info("Loading all car categories");
        return categoryRepository.findAll();
    }

    public CarCategory findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Category was not found: {}", id);
                    return new IllegalArgumentException("Category not found");
                });
    }

    public CarCategory save(CarCategory category) {
        log.info("Saving category {}", category.getName());
        return categoryRepository.save(category);
    }
}

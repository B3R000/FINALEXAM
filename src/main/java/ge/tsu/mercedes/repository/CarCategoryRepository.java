package ge.tsu.mercedes.repository;

import ge.tsu.mercedes.model.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarCategoryRepository extends JpaRepository<CarCategory, Long> {
}

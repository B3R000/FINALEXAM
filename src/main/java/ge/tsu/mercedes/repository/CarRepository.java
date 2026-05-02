package ge.tsu.mercedes.repository;

import ge.tsu.mercedes.model.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByOrderByIdDesc(Pageable pageable);
}

package ge.tsu.mercedes.dto;

import ge.tsu.mercedes.model.Car;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CarDto(
        Long id,
        @NotBlank @Size(min = 2, max = 80) String model,
        @NotNull @Min(2010) Integer year,
        @NotNull @DecimalMin("1000.00") BigDecimal price,
        @NotBlank String color,
        @NotBlank @Email String contactEmail,
        @NotNull Long categoryId,
        String categoryName
) {

    public static CarDto fromEntity(Car car) {
        return new CarDto(
                car.getId(),
                car.getModel(),
                car.getYear(),
                car.getPrice(),
                car.getColor(),
                car.getContactEmail(),
                car.getCategory() == null ? null : car.getCategory().getId(),
                car.getCategory() == null ? null : car.getCategory().getName()
        );
    }

    public Car toEntity() {
        Car car = new Car();
        car.setId(id);
        car.setModel(model);
        car.setYear(year);
        car.setPrice(price);
        car.setColor(color);
        car.setContactEmail(contactEmail);
        return car;
    }
}

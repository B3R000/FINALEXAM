package ge.tsu.mercedes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "მოდელის დასახელება აუცილებელია")
    @Size(min = 2, max = 80, message = "მოდელი უნდა იყოს 2-80 სიმბოლო")
    private String model;

    @NotNull(message = "გამოშვების წელი აუცილებელია")
    @Min(value = 2010, message = "წელი უნდა იყოს მინიმუმ 2010")
    @Column(name = "manufacture_year")
    private Integer year;

    @NotNull(message = "ფასი აუცილებელია")
    @DecimalMin(value = "1000.00", message = "ფასი უნდა იყოს მინიმუმ 1000")
    private BigDecimal price;

    @NotBlank(message = "ფერი აუცილებელია")
    private String color;

    @NotBlank(message = "საკონტაქტო ელფოსტა აუცილებელია")
    @Email(message = "ელფოსტა არასწორი ფორმატითაა")
    private String contactEmail;

    private String imageFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "კატეგორია აუცილებელია")
    private CarCategory category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }
}

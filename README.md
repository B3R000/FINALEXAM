# showroom

Spring Boot-ზე აწყობილი Mercedes-Benz შოურუმის ვებ აპლიკაცია.

## გაშვება

1. დააყენე JDK 17 ან უფრო ახალი.
2. დააყენე Maven.
3. პროექტის საქაღალდეში გაუშვი:

```powershell
mvn spring-boot:run
```

აპლიკაცია გაიხსნება მისამართზე:

```text
http://localhost:8080
```

H2 Console:

```text
http://localhost:8080/h2-console
```

JDBC URL:

```text
jdbc:h2:mem:mercedesdb
```

## მოთხოვნების დაფარვა

- Maven Spring Boot სტრუქტურა: `pom.xml`, `src/main/java`, `src/main/resources`.
- Static რესურსები: `src/main/resources/static/css/styles.css`, `src/main/resources/static/js/app.js`, `src/main/resources/static/images`.
- Thymeleaf templates: `src/main/resources/templates`.
- Logging: `application.yml`-ში console/file logging და `ge.tsu.mercedes` პაკეტის DEBUG დონე.
- კოდში ლოგირება: `info`, `warn`, `error` დონეები Service და Initializer კლასებში.
- HTML POST ფორმები: ავტომობილის და კატეგორიის დამატება.
- File upload: ავტომობილის ფოტოს ატვირთვა `MultipartFile`-ით.
- Bean Validation: `@NotBlank`, `@Size`, `@Email`, `@NotNull`, `@Min`, `@DecimalMin`.
- Validation errors: ფორმებში `th:errors`.
- PRG: წარმატებული POST შემდეგ `redirect:/cars` და `redirect:/categories`.
- Database: H2 + Spring Data JPA.
- Entity კლასები: `Car`, `CarCategory`.
- Repository ინტერფეისები: `CarRepository`, `CarCategoryRepository`.
- Service კლასები: `CarService`, `CarCategoryService`.
- Relation: `CarCategory` -> `Car` (`@OneToMany`) და `Car` -> `CarCategory` (`@ManyToOne`).
- CRUD: ავტომობილების Create, Read, Update, Delete.
- Custom configuration: `showroom.name`, `showroom.support-email`, `showroom.upload-dir`.
- Profiles: `application-dev.yml`, `application-prod.yml`.
- REST API: `/api/cars` GET, POST, DELETE JSON DTO-ებით.
